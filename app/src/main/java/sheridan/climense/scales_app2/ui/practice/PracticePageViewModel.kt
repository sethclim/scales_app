package sheridan.climense.scales_app2.ui.practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PracticePageViewModel : ViewModel() {

    var practiceArray : Array<String> = emptyArray()
    val _item = MutableLiveData<String>()
    var item: LiveData<String> = _item

    fun next(){
        _item.value = practiceArray.random()
    }
}
