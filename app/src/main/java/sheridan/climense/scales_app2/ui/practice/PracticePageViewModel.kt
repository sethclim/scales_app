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

    var scaleCount = 0
    var arpCount = 0
    var solidCount = 0
    var brokenCount = 0
    var octCount = 0
    var cmCount = 0


    fun next(){
        PracticeCycler.nextScale()
        _item.value = PracticeCycler.currentScale
        getCount()
    }
    private fun getCount(){
            when(item.value!!.tech){
                "Scale" -> scaleCount += 1
                "Arp" -> arpCount += 1
                "Solid" -> solidCount += 1
                "Broken" -> brokenCount += 1
                "Oct" -> octCount += 1
                "C.M." -> cmCount += 1
            }
    }
}


