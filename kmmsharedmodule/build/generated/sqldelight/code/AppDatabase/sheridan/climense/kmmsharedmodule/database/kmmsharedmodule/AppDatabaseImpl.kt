package sheridan.climense.kmmsharedmodule.database.kmmsharedmodule

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass
import sheridan.climense.kmmsharedmodule.database.AppDatabase
import sheridan.climense.kmmsharedmodule.database.AppDatabaseQueries
import sheridan.climense.kmmsharedmodule.database.Favourites
import sheridan.climense.kmmsharedmodule.database.InProgressItems
import sheridan.climense.kmmsharedmodule.database.PracticeRecord
import sheridan.climense.kmmsharedmodule.database.RoutineItems
import sheridan.climense.kmmsharedmodule.database.Routines
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(
  driver: SqlDriver,
  FavouritesAdapter: Favourites.Adapter,
  InProgressItemsAdapter: InProgressItems.Adapter,
  RoutineItemsAdapter: RoutineItems.Adapter
): AppDatabase = AppDatabaseImpl(driver, FavouritesAdapter, InProgressItemsAdapter,
    RoutineItemsAdapter)

private class AppDatabaseImpl(
  driver: SqlDriver,
  internal val FavouritesAdapter: Favourites.Adapter,
  internal val InProgressItemsAdapter: InProgressItems.Adapter,
  internal val RoutineItemsAdapter: RoutineItems.Adapter
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE PracticeRecord (
          |  date INTEGER NOT NULL PRIMARY KEY UNIQUE,
          |  scales INTEGER NOT NULL,
          |  arps INTEGER NOT NULL,
          |  oct INTEGER NOT NULL,
          |  solid INTEGER NOT NULL,
          |  broken INTEGER NOT NULL,
          |  conMotion INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE Favourites (
          |  key INTEGER NOT NULL PRIMARY KEY UNIQUE,
          |  root TEXT NOT NULL,
          |  scale TEXT NOT NULL,
          |  tech TEXT NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE Routines(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
          |    title TEXT NOT NULL,
          |    date INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE InProgressItems(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    routineref INTEGER NOT NULL,
          |    root TEXT NOT NULL,
          |    scale TEXT NOT NULL,
          |    tech TEXT NOT NULL,
          |    FOREIGN KEY(routineref) REFERENCES Routines(id)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE RoutineItems(
          |    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
          |    routineref INTEGER NOT NULL,
          |    root TEXT NOT NULL,
          |    scale TEXT NOT NULL,
          |    tech TEXT NOT NULL,
          |    FOREIGN KEY(routineref) REFERENCES Routines(id)
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getAllPracticeSessions: MutableList<Query<*>> = copyOnWriteList()

  internal val getPracticeSeesionRange: MutableList<Query<*>> = copyOnWriteList()

  internal val getAllFavourites: MutableList<Query<*>> = copyOnWriteList()

  internal val getAllRoutines: MutableList<Query<*>> = copyOnWriteList()

  internal val lastInsertRowIdRoutines: MutableList<Query<*>> = copyOnWriteList()

  internal val getRoutineItemsById: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getAllPracticeSessions(mapper: (
    date: Long,
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long
  ) -> T): Query<T> = Query(80920930, getAllPracticeSessions, driver, "AppDatabase.sq",
      "getAllPracticeSessions", "SELECT * FROM PracticeRecord") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getLong(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getLong(6)!!
    )
  }

  public override fun getAllPracticeSessions(): Query<PracticeRecord> = getAllPracticeSessions {
      date, scales, arps, oct, solid, broken, conMotion ->
    PracticeRecord(
      date,
      scales,
      arps,
      oct,
      solid,
      broken,
      conMotion
    )
  }

  public override fun <T : Any> getPracticeSeesionRange(
    startDate: Long,
    endDate: Long,
    mapper: (
      date: Long,
      scales: Long,
      arps: Long,
      oct: Long,
      solid: Long,
      broken: Long,
      conMotion: Long
    ) -> T
  ): Query<T> = GetPracticeSeesionRangeQuery(startDate, endDate) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getLong(2)!!,
      cursor.getLong(3)!!,
      cursor.getLong(4)!!,
      cursor.getLong(5)!!,
      cursor.getLong(6)!!
    )
  }

  public override fun getPracticeSeesionRange(startDate: Long, endDate: Long): Query<PracticeRecord>
      = getPracticeSeesionRange(startDate, endDate) { date, scales, arps, oct, solid, broken,
      conMotion ->
    PracticeRecord(
      date,
      scales,
      arps,
      oct,
      solid,
      broken,
      conMotion
    )
  }

  public override fun <T : Any> getAllFavourites(mapper: (
    key: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ) -> T): Query<T> = Query(306479828, getAllFavourites, driver, "AppDatabase.sq",
      "getAllFavourites", "SELECT * FROM Favourites") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      database.FavouritesAdapter.rootAdapter.decode(cursor.getString(1)!!),
      database.FavouritesAdapter.scaleAdapter.decode(cursor.getString(2)!!),
      database.FavouritesAdapter.techAdapter.decode(cursor.getString(3)!!)
    )
  }

  public override fun getAllFavourites(): Query<Favourites> = getAllFavourites { key, root, scale,
      tech ->
    Favourites(
      key,
      root,
      scale,
      tech
    )
  }

  public override fun <T : Any> getAllRoutines(mapper: (
    id: Long,
    title: String,
    date: Long
  ) -> T): Query<T> = Query(-1161217895, getAllRoutines, driver, "AppDatabase.sq", "getAllRoutines",
      "SELECT * FROM Routines") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getLong(2)!!
    )
  }

  public override fun getAllRoutines(): Query<Routines> = getAllRoutines { id, title, date ->
    Routines(
      id,
      title,
      date
    )
  }

  public override fun lastInsertRowIdRoutines(): Query<Long> = Query(-1177035466,
      lastInsertRowIdRoutines, driver, "AppDatabase.sq", "lastInsertRowIdRoutines",
      "SELECT id FROM Routines") { cursor ->
    cursor.getLong(0)!!
  }

  public override fun <T : Any> getRoutineItemsById(id: Long, mapper: (
    id: Long,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ) -> T): Query<T> = GetRoutineItemsByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      database.RoutineItemsAdapter.rootAdapter.decode(cursor.getString(2)!!),
      database.RoutineItemsAdapter.scaleAdapter.decode(cursor.getString(3)!!),
      database.RoutineItemsAdapter.techAdapter.decode(cursor.getString(4)!!)
    )
  }

  public override fun getRoutineItemsById(id: Long): Query<RoutineItems> = getRoutineItemsById(id) {
      id_, routineref, root, scale, tech ->
    RoutineItems(
      id_,
      routineref,
      root,
      scale,
      tech
    )
  }

  public override fun insertPracticeSession(
    date: Long?,
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long
  ): Unit {
    driver.execute(229631779,
        """INSERT INTO PracticeRecord(date, scales, arps, oct, solid, broken, conMotion) VALUES (?,?,?,?,?,?,?)""",
        7) {
      bindLong(1, date)
      bindLong(2, scales)
      bindLong(3, arps)
      bindLong(4, oct)
      bindLong(5, solid)
      bindLong(6, broken)
      bindLong(7, conMotion)
    }
    notifyQueries(229631779, {database.appDatabaseQueries.getPracticeSeesionRange +
        database.appDatabaseQueries.getAllPracticeSessions})
  }

  public override fun updatePracticeSession(
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long,
    date: Long
  ): Unit {
    driver.execute(-1096577261,
        """UPDATE PracticeRecord SET scales = ?, arps = ?, oct = ?, solid = ?, broken = ?, conMotion = ? WHERE date = ?""",
        7) {
      bindLong(1, scales)
      bindLong(2, arps)
      bindLong(3, oct)
      bindLong(4, solid)
      bindLong(5, broken)
      bindLong(6, conMotion)
      bindLong(7, date)
    }
    notifyQueries(-1096577261, {database.appDatabaseQueries.getPracticeSeesionRange +
        database.appDatabaseQueries.getAllPracticeSessions})
  }

  public override fun deletePracticeSession(date: Long): Unit {
    driver.execute(1948361201, """DELETE FROM PracticeRecord WHERE date = ?""", 1) {
      bindLong(1, date)
    }
    notifyQueries(1948361201, {database.appDatabaseQueries.getPracticeSeesionRange +
        database.appDatabaseQueries.getAllPracticeSessions})
  }

  public override fun deleteAllPracticeSessions(): Unit {
    driver.execute(644218063, """DELETE FROM PracticeRecord""", 0)
    notifyQueries(644218063, {database.appDatabaseQueries.getPracticeSeesionRange +
        database.appDatabaseQueries.getAllPracticeSessions})
  }

  public override fun insertFavourite(
    key: Long?,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit {
    driver.execute(344198385, """INSERT INTO Favourites(key, root, scale, tech) VALUES (?,?,?,?)""",
        4) {
      bindLong(1, key)
      bindString(2, database.FavouritesAdapter.rootAdapter.encode(root))
      bindString(3, database.FavouritesAdapter.scaleAdapter.encode(scale))
      bindString(4, database.FavouritesAdapter.techAdapter.encode(tech))
    }
    notifyQueries(344198385, {database.appDatabaseQueries.getAllFavourites})
  }

  public override fun deleteFavourites(key: Long): Unit {
    driver.execute(341429780, """DELETE FROM Favourites WHERE key = ?""", 1) {
      bindLong(1, key)
    }
    notifyQueries(341429780, {database.appDatabaseQueries.getAllFavourites})
  }

  public override fun deleteAllFavourites(): Unit {
    driver.execute(1141904385, """DELETE FROM Favourites""", 0)
    notifyQueries(1141904385, {database.appDatabaseQueries.getAllFavourites})
  }

  public override fun insertRoutine(
    id: Long?,
    title: String,
    date: Long
  ): Unit {
    driver.execute(2098478860, """INSERT INTO Routines(id, title, date) VALUES (?,?,?)""", 3) {
      bindLong(1, id)
      bindString(2, title)
      bindLong(3, date)
    }
    notifyQueries(2098478860, {database.appDatabaseQueries.lastInsertRowIdRoutines +
        database.appDatabaseQueries.getAllRoutines})
  }

  public override fun deleteRoutine(id: Long): Unit {
    driver.execute(-1889753126, """DELETE FROM Routines WHERE id = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(-1889753126, {database.appDatabaseQueries.lastInsertRowIdRoutines +
        database.appDatabaseQueries.getAllRoutines})
  }

  public override fun deleteAllRoutineInfo(): Unit {
    driver.execute(-2048492709, """DELETE FROM Routines""", 0)
    notifyQueries(-2048492709, {database.appDatabaseQueries.lastInsertRowIdRoutines +
        database.appDatabaseQueries.getAllRoutines})
  }

  public override fun insertRoutineItem(
    id: Long?,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit {
    driver.execute(1269352511,
        """INSERT INTO RoutineItems(id, routineref, root, scale, tech) VALUES (?,?,?, ?, ?)""", 5) {
      bindLong(1, id)
      bindLong(2, routineref)
      bindString(3, database.RoutineItemsAdapter.rootAdapter.encode(root))
      bindString(4, database.RoutineItemsAdapter.scaleAdapter.encode(scale))
      bindString(5, database.RoutineItemsAdapter.techAdapter.encode(tech))
    }
    notifyQueries(1269352511, {database.appDatabaseQueries.getRoutineItemsById})
  }

  public override fun deleteRoutineItems(id: Long): Unit {
    driver.execute(464463878, """DELETE FROM RoutineItems WHERE routineref = ?""", 1) {
      bindLong(1, id)
    }
    notifyQueries(464463878, {database.appDatabaseQueries.getRoutineItemsById})
  }

  public override fun insertPracticeItem(
    id: Long?,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit {
    driver.execute(1454254502,
        """INSERT INTO InProgressItems(id, routineref, root, scale, tech) VALUES (?,?,?, ?, ?)""",
        5) {
      bindLong(1, id)
      bindLong(2, routineref)
      bindString(3, database.InProgressItemsAdapter.rootAdapter.encode(root))
      bindString(4, database.InProgressItemsAdapter.scaleAdapter.encode(scale))
      bindString(5, database.InProgressItemsAdapter.techAdapter.encode(tech))
    }
  }

  public override fun deleteAllRoutineItems(): Unit {
    driver.execute(921413299, """DELETE FROM RoutineItems""", 0)
    notifyQueries(921413299, {database.appDatabaseQueries.getRoutineItemsById})
  }

  private inner class GetPracticeSeesionRangeQuery<out T : Any>(
    public val startDate: Long,
    public val endDate: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getPracticeSeesionRange, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-405548057,
        """SELECT * FROM PracticeRecord WHERE date BETWEEN ? AND ? ORDER BY date ASC""", 2) {
      bindLong(1, startDate)
      bindLong(2, endDate)
    }

    public override fun toString(): String = "AppDatabase.sq:getPracticeSeesionRange"
  }

  private inner class GetRoutineItemsByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getRoutineItemsById, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(670843205,
        """SELECT * FROM RoutineItems WHERE routineref = ?""", 1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "AppDatabase.sq:getRoutineItemsById"
  }
}
