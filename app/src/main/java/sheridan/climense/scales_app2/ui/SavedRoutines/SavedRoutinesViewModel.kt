package sheridan.climense.scales_app2.ui.SavedRoutines

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.SavedRoutine

class SavedRoutinesViewModel(application: Application) : AndroidViewModel(application) {

    private val practiceDao : PracticeDao = PracticeDatabase.getInstance(application).practiceDao

    val savedRoutines : LiveData<List<SavedRoutine>> = practiceDao.getSaved()

}