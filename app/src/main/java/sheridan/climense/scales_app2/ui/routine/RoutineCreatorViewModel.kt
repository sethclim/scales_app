package sheridan.climense.scales_app2.ui.routine

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.climense.scales_app2.BR
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.model.RoutineInputs


class RoutineCreatorViewModel : ViewModel() {

    val _mutableInputs : MutableLiveData<RoutineInputs> = MutableLiveData<RoutineInputs>()
    var mutableInputs : LiveData<RoutineInputs> = _mutableInputs

    fun generateRoutine(){
        val myScales = RoutineInputs.getScales()

        RoutineGenerator.generate(myScales)


    }


}