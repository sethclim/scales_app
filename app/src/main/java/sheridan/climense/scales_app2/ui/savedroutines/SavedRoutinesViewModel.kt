package sheridan.climense.scales_app2.ui.savedroutines

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.models.PracticeSave

class SavedRoutinesViewModel(application: Application) : AndroidViewModel(application) {

    private val practiceDao : PracticeDao = PracticeDatabase.getInstance(application).practiceDao

    val savedRoutines : LiveData<List<SavedRoutine>> = practiceDao.getSaved()

    val favourites : LiveData<Array<PracticeSave>> = practiceDao.getFavourites()

    fun removeSavedroutine(key : Long){
        viewModelScope.launch{
            practiceDao.deleteSavedRoutine(key)
        }

    }

}