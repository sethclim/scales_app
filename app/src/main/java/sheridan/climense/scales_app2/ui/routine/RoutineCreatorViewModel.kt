package sheridan.climense.scales_app2.ui.routine

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.models.PracticeSave
import sheridan.climense.scales_app2.models.RoutineInputs
import java.util.*

class RoutineCreatorViewModel(application: Application) : AndroidViewModel(application) {

    private val _mutableInputs : MutableLiveData<RoutineInputs> = MutableLiveData<RoutineInputs>()
    var mutableInputs : LiveData<RoutineInputs> = _mutableInputs

    var routine : Array<PracticeSave> = arrayOf()

    private val practiceDao: PracticeDao =
            PracticeDatabase.getInstance(application).practiceDao

    fun generateRoutine() : Boolean{
        viewModelScope.launch {
            val pr = practiceDao.getNLFavourites()
            //RoutineGenerator.favourites =  Practice(pr.root, pr.scale, pr.tech)
        }
       // routine =  RoutineGenerator.generate()

        return routine.isNotEmpty()
    }

    fun saveRoutine(name : String, date : Date){
        generateRoutine()
        viewModelScope.launch {
            val savedRoutine = SavedRoutine(0L, name, routine,null, 0,routine.size, date)
            practiceDao.insert(savedRoutine)
        }
    }
}