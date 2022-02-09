package sheridan.climense.scales_app2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.scales_app2.models.PracticeSave
import java.time.LocalDate

@Dao
interface PracticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(practiceRecord: PracticeRecord): Long

    @Insert
    suspend fun insert(savedRoutine: SavedRoutine): Long

    @Insert
    suspend fun insert(favourites: Favourites): Long

    @Update
    fun update(practiceRecord: PracticeRecord): Int

    @Update
    suspend fun update(savedRoutine: SavedRoutine): Int

    @Query("SELECT * FROM PracticeRecord WHERE date = :date")
    suspend fun getDate(date : LocalDate): PracticeRecord?

    @Query("SELECT * FROM PracticeRecord ORDER BY date")
    fun getAll() : LiveData<List<PracticeRecord>>

    @Query("SELECT * FROM PracticeRecord WHERE date BETWEEN :startDate AND :endDate  ORDER BY date")
    fun getLastSevenDays(startDate : LocalDate, endDate : LocalDate) : LiveData<List<PracticeRecord>>

    @Query("DELETE FROM PracticeRecord")
    suspend fun deletePracticeRecord()

    @Query("DELETE FROM SavedRoutine")
    suspend fun deleteSavedRoutines()

    @Query("DELETE FROM Favourites")
    suspend fun deleteFavorites()

    @Query("DELETE FROM Favourites WHERE `key`=:ID")
    suspend fun deleteFavourite(ID : String)

    @Query("SELECT `Key` FROM Favourites WHERE `key`=:ID")
    suspend fun selectFavourite(ID : String) : String

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

    @Query("SELECT Root, Scale, Tech, Fav, `Key` FROM  Favourites")
    fun getFavourites() : LiveData<Array<PracticeSave>>

    @Query("SELECT Root, Scale, Tech, Fav, `Key` FROM  Favourites")
    suspend fun getNLFavourites() : Array<PracticeSave>


}