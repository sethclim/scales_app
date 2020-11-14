package sheridan.climense.scales_app2.ui.routine


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.model.RoutineInputs


class RoutineCreatorViewModel : ViewModel() {

    val _mutableInputs : MutableLiveData<RoutineInputs> = MutableLiveData<RoutineInputs>()
    var mutableInputs : LiveData<RoutineInputs> = _mutableInputs

    var routine : Array<RoutineGenerator.Companion.practice> = arrayOf()

    fun generateRoutine(){
        routine =  RoutineGenerator.generate()
    }




}