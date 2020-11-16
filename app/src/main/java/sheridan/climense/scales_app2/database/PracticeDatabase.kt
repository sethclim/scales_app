package sheridan.climense.scales_app2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PracticeRecord::class], version = 1, exportSchema = false)
abstract class SavedRollDatabase : RoomDatabase() {

    abstract val practiceDao: PracticeDao

    companion object{

        @Volatile
        private var INSTANCE: SavedRollDatabase? = null

        fun getInstance(context: Context): SavedRollDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SavedRollDatabase::class.java,
                            "practice_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}