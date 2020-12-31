package sheridan.climense.scales_app2.ui.practicehistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.PracticeRecord
import java.time.LocalDate

class PracticeHistoryPageViewModel(application: Application) : AndroidViewModel(application)  {

    private val practiceDao: PracticeDao =
        PracticeDatabase.getInstance(application).practiceDao

    fun getCurrentDateTime(): LocalDate {
        val date = LocalDate.now()
        return date
    }




    val allPractice : LiveData<List<PracticeRecord>> = practiceDao.getLastSevenDays(getCurrentDateTime().minusDays(
        getCurrentDateTime().dayOfWeek.value.toLong()+1L
    ),getCurrentDateTime() )

}