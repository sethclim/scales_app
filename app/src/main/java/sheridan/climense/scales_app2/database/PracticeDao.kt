package sheridan.climense.scales_app2.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import sheridan.climense.scales_app2.model.PracticeCycler
import sheridan.climense.scales_app2.model.RoutineGenerator
import java.util.*

@Dao
interface PracticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(practiceRecord: PracticeRecord): Long

    @Insert
    suspend fun insert(savedRoutine: SavedRoutine): Long

    @Update
    fun update(practiceRecord: PracticeRecord): Int

    @Update
    suspend fun update(savedRoutine: SavedRoutine): Int

    @Query("SELECT * FROM PracticeRecord WHERE date = :date")
    fun getDate(date : String): LiveData<PracticeRecord>?

    @Query("SELECT * FROM PracticeRecord ORDER BY date")
    fun getAll() : LiveData<List<PracticeRecord>>

    @Query("DELETE FROM PracticeRecord")
    suspend fun deleteAll()

    @Query("SELECT SUM(scales) FROM PracticeRecord")
    fun getTotal() : LiveData<Int>

    @Transaction
    suspend fun insertOrUpdate(practiceRecord: PracticeRecord) {
        val id = insert(practiceRecord)
        if (id == -1L) update(practiceRecord)
    }

    @Query("SELECT * FROM savedroutine")
    fun getSaved() : LiveData<List<SavedRoutine>>

    @Query("DELETE FROM SavedRoutine WHERE `Key`=:ID")
    suspend fun deleteSavedRoutine(ID : Long)


}