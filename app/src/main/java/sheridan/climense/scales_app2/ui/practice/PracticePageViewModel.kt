package sheridan.climense.scales_app2.ui.practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import sheridan.climense.scales_app2.model.PracticeCycler
import sheridan.climense.scales_app2.model.RoutineGenerator

class PracticePageViewModel : ViewModel() {

    val _item = MutableLiveData<RoutineGenerator.Companion.practice>()
    var item: LiveData<RoutineGenerator.Companion.practice> = _item

    val PracticeString = Transformations.map(item){scales -> "${scales.root} ${scales.scale} ${scales.tech}"}

    fun next(){
        PracticeCycler.nextScale()
        _item.value = PracticeCycler.currentScale
    }
}


