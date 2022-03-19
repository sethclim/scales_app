package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getAllPracticeSessions(mapper: (
    date: Long,
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long
  ) -> T): Query<T>

  public fun getAllPracticeSessions(): Query<PracticeRecord>

  public fun <T : Any> getPracticeSeesionRange(
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
  ): Query<T>

  public fun getPracticeSeesionRange(startDate: Long, endDate: Long): Query<PracticeRecord>

  public fun <T : Any> getAllFavourites(mapper: (
    key: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ) -> T): Query<T>

  public fun getAllFavourites(): Query<Favourites>

  public fun <T : Any> getAllRoutines(mapper: (
    id: Long,
    title: String,
    date: Long
  ) -> T): Query<T>

  public fun getAllRoutines(): Query<Routines>

  public fun lastInsertRowIdRoutines(): Query<Long>

  public fun <T : Any> getRoutineItemsById(id: Long, mapper: (
    id: Long,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ) -> T): Query<T>

  public fun getRoutineItemsById(id: Long): Query<RoutineItems>

  public fun insertPracticeSession(
    date: Long?,
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long
  ): Unit

  public fun updatePracticeSession(
    scales: Long,
    arps: Long,
    oct: Long,
    solid: Long,
    broken: Long,
    conMotion: Long,
    date: Long
  ): Unit

  public fun deletePracticeSession(date: Long): Unit

  public fun deleteAllPracticeSessions(): Unit

  public fun insertFavourite(
    key: Long?,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit

  public fun deleteFavourites(key: Long): Unit

  public fun deleteAllFavourites(): Unit

  public fun insertRoutine(
    id: Long?,
    title: String,
    date: Long
  ): Unit

  public fun deleteRoutine(id: Long): Unit

  public fun deleteAllRoutineInfo(): Unit

  public fun insertRoutineItem(
    id: Long?,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit

  public fun deleteRoutineItems(id: Long): Unit

  public fun insertPracticeItem(
    id: Long?,
    routineref: Long,
    root: RootType,
    scale: ScaleType,
    tech: TechType
  ): Unit

  public fun deleteAllRoutineItems(): Unit
}
