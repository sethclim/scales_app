package sheridan.climense.scales_app2.database

import android.content.Context
import androidx.room.*

@Database(entities = [PracticeRecord::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class PracticeDatabase : RoomDatabase() {

    abstract val practiceDao: PracticeDao

    companion object{

        @Volatile
        private var INSTANCE: PracticeDatabase? = null

        fun getInstance(context: Context): PracticeDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PracticeDatabase::class.java,
                        "practice_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}