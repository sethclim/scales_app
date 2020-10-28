package sheridan.climense.scales_app2.ui.practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.climense.scales_app2.model.PracticeCycler

class PracticePageViewModel : ViewModel() {

    val _item = MutableLiveData<String>()
    var item: LiveData<String> = _item

    fun next(){
        PracticeCycler.nextScale()
        _item.value = PracticeCycler.currentScale
    }
}
