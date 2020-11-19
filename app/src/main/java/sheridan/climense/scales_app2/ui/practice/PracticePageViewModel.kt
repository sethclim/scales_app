package sheridan.climense.scales_app2.ui.practice

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.Converters
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.PracticeRecord
import sheridan.climense.scales_app2.model.PracticeCycler
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.util.DateConverters
import java.util.*

class PracticePageViewModel(application: Application) : AndroidViewModel(application) {

    val _item = MutableLiveData<RoutineGenerator.Companion.practice>()
    var item: LiveData<RoutineGenerator.Companion.practice> = _item

    val PracticeString = Transformations.map(item){scales -> "${scales.root} ${scales.scale} ${scales.tech}"}

    private val practiceDao: PracticeDao =
        PracticeDatabase.getInstance(application).practiceDao

    val timeStamp : String? = DateConverters.formatDate (getCurrentDateTime())

    val record : LiveData<PracticeRecord> = practiceDao.getDate(timeStamp!!)

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

    fun getCurrentDateTime(): Date
    {
        val cal = Calendar.getInstance().time
        return cal
    }



    fun saveRecord(){
        viewModelScope.launch {
            practiceDao.insertOrUpdate(PracticeRecord(scaleCount,arpCount,octCount,solidCount,brokenCount, cmCount, timeStamp!!))
        }
    }

    fun loadRecord(practiceRecord: PracticeRecord){
        scaleCount = practiceRecord.scales
        arpCount = practiceRecord.arps
        solidCount = practiceRecord.solid
        brokenCount = practiceRecord.broken
        octCount = practiceRecord.oct
        cmCount = practiceRecord.conMotion
    }
}