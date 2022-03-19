package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import sheridan.climense.kmmsharedmodule.database.kmmsharedmodule.newInstance
import sheridan.climense.kmmsharedmodule.database.kmmsharedmodule.schema

public interface AppDatabase : Transacter {
  public val appDatabaseQueries: AppDatabaseQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    public operator fun invoke(
      driver: SqlDriver,
      FavouritesAdapter: Favourites.Adapter,
      InProgressItemsAdapter: InProgressItems.Adapter,
      RoutineItemsAdapter: RoutineItems.Adapter
    ): AppDatabase = AppDatabase::class.newInstance(driver, FavouritesAdapter,
        InProgressItemsAdapter, RoutineItemsAdapter)
  }
}
