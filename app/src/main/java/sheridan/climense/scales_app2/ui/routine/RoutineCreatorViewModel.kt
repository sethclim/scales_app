package sheridan.climense.scales_app2.ui.routine


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.model.RoutineInputs


class RoutineCreatorViewModel(application: Application) : AndroidViewModel(application) {

    val _mutableInputs : MutableLiveData<RoutineInputs> = MutableLiveData<RoutineInputs>()
    var mutableInputs : LiveData<RoutineInputs> = _mutableInputs

    var routine : Array<RoutineGenerator.Companion.practice> = arrayOf()

    private val practiceDao: PracticeDao =
            PracticeDatabase.getInstance(application).practiceDao

    fun generateRoutine(){
        routine =  RoutineGenerator.generate()
    }

    fun saveRoutine(name : String){

        generateRoutine()
//        Log.d("routineinVM", routine[1].toString())
        viewModelScope.launch {
            val savedRoutine = SavedRoutine(0L, name, routine, 50, 45604125 )
            practiceDao.insert(savedRoutine)
        }
    }
}