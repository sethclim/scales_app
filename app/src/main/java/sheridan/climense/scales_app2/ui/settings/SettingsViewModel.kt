package sheridan.climense.scales_app2.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase

class SettingsViewModel (application: Application) : AndroidViewModel(application) {

    private val practiceDao : PracticeDao = PracticeDatabase.getInstance(application).practiceDao

    var deleteNum = -1

    fun deleteFavs(){
        viewModelScope.launch {
            practiceDao.deleteFavorites()
        }
        deleteNum = -1
    }
    fun deleteRoutines(){
        viewModelScope.launch {
            practiceDao.deleteSavedRoutines()
        }
        deleteNum = -1
    }
    fun deleteHistory(){
        viewModelScope.launch {
            practiceDao.deletePracticeRecord()
        }
        deleteNum = -1
    }
    fun deleteAll(){
        viewModelScope.launch {
            practiceDao.deleteFavorites()
            practiceDao.deletePracticeRecord()
            practiceDao.deleteSavedRoutines()
        }
        deleteNum = -1
    }
}