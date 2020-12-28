package sheridan.climense.scales_app2.ui.practice

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.PracticeDao
import sheridan.climense.scales_app2.database.PracticeDatabase
import sheridan.climense.scales_app2.database.PracticeRecord
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.model.PracticeCycler
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.util.DateConverters
import java.util.*

class PracticePageViewModel(application: Application) : AndroidViewModel(application) {

    val _item = MutableLiveData<RoutineGenerator.Companion.practice>()
    var item: LiveData<RoutineGenerator.Companion.practice> = _item


    var _isEnd = MutableLiveData(false)
    var isEnd : LiveData<Boolean> = _isEnd

    var done = false

    val _msg = MutableLiveData("You're Done")
    var msg : LiveData<String> = _msg

    val practiceString  =  Transformations.map(item){scales -> "${scales.root} ${scales.scale} ${scales.tech}"}


    private val _progress = MutableLiveData<Int>()
    var progress : LiveData<Int> = _progress

    private val _pMax = MutableLiveData<Int>()
    var pMax : LiveData<Int> = _pMax



    private val practiceDao: PracticeDao =
        PracticeDatabase.getInstance(application).practiceDao

    val timeStamp : String? = DateConverters.formatDate (getCurrentDateTime())

    val record : LiveData<PracticeRecord>? = practiceDao.getDate(timeStamp!!)
//    val record : LiveData<PracticeRecord>? = _record
//        get() = if(field.value != null){
//        return field
//        }

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
        _isEnd.value = true
    }

    fun getProgress(oLength : Int) {
        val cSize = PracticeCycler.practiceArray.size
        Log.d("cSize in VM", cSize.toString())
        _progress.value = oLength -  cSize
        _pMax.value = oLength

        if(cSize == 0){
            _msg.value = "You're Done"
            _isEnd.value = false
            done = true
        }
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

    fun getCurrentDateTime(): Date {
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

    fun updatedSavedProgress(key : Long, title : String, routine: Array<RoutineGenerator.Companion.practice>,inProgress: Array<RoutineGenerator.Companion.practice>, total : Int,date : Date ) {
        viewModelScope.launch {
            practiceDao.update(SavedRoutine(key,title,routine,inProgress,progress.value!!,total, date))
        }
    }
}