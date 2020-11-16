package sheridan.climense.scales_app2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PracticeDao {
    @Insert
    suspend fun insert(practiceRecord: PracticeRecord): Long

    @Query("SELECT * FROM practicerecord WHERE id=:key")
    fun get(key: Long) : LiveData<PracticeRecord>

    @Query("SELECT * FROM practicerecord ORDER BY id")
    fun getAll() : LiveData<List<PracticeRecord>>

    @Query("SELECT SUM(scales) FROM practicerecord")
    fun getTotal() : LiveData<Int>

    @Query("DELETE FROM practicerecord")
    suspend fun deleteAll()

}