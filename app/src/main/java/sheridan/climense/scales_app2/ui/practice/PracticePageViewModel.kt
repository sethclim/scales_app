package sheridan.climense.scales_app2.ui.practice

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.climense.scales_app2.database.*
import sheridan.climense.kmmsharedmodule.domain.PracticeCycler
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.TechType
import sheridan.climense.scales_app2.models.PracticeSave
import java.time.LocalDate
import java.util.*


class PracticePageViewModel(application: Application) : AndroidViewModel(application) {

    private val _item = MutableLiveData<Practice>()
    var item: LiveData<Practice> = _item

    var _isEnd = MutableLiveData(false)
    var isEnd : LiveData<Boolean> = _isEnd

    var done = false

    var _isFav = MutableLiveData(false)
    var isFav : LiveData<Boolean> = _isFav

    val _msg = MutableLiveData("You're Done")
    var msg : LiveData<String> = _msg

    val practiceString  =  Transformations.map(item){scales -> "${scales.root} ${scales.scale} ${scales.tech}"}

    private val _progress = MutableLiveData<Int>()
    var progress : LiveData<Int> = _progress

    private val _pMax = MutableLiveData<Int>()
    var pMax : LiveData<Int> = _pMax



    private val practiceDao: PracticeDao =
        PracticeDatabase.getInstance(application).practiceDao

//    /val timeStamp : String? = DateConverters.formatDate (getCurrentDateTime())

//    var record : PracticeRecord? = null

    var scaleCount = 0
    var arpCount = 0
    var solidCount = 0
    var brokenCount = 0
    var octCount = 0
    var cmCount = 0

    fun next(){
        PracticeCycler.nextScale()
        PracticeCycler.currentScale.let {  _item.value =  PracticeCycler.currentScale!! }

        viewModelScope.launch{
            val key = item.value!!.root.strName+item.value!!.scale+item.value!!.tech
            val fav = practiceDao.selectFavourite(key)

            _isFav.value = fav == key
        }

        getCount()
        _isEnd.value = true
    }

    fun getProgress(oLength : Int) {
        val cSize =  PracticeCycler.practiceArray.size
        _progress.value = oLength - cSize as Int
        _pMax.value = oLength

        if(cSize == 0){
            _msg.value = "You're Done"
            _isEnd.value = false
            done = true
        }
    }

    private fun getCount(){
        when(item.value!!.tech){
            TechType.Scale -> scaleCount += 1
            TechType.Arp -> arpCount += 1
            TechType.Solid -> solidCount += 1
            TechType.Broken -> brokenCount += 1
            TechType.Oct -> octCount += 1
            TechType.CM -> cmCount += 1
        }
    }

    fun getCurrentDateTime(): LocalDate {
        val date = LocalDate.now()
        return date
    }


    fun saveRecord(){
        viewModelScope.launch {
            val record = practiceDao.getDate(getCurrentDateTime())
            if(record != null){
                val scale = record.scales + scaleCount
                val arp = record.arps + arpCount
                val oct = record.oct + octCount
                val solid = record.solid + solidCount
                val broke = record.broken + brokenCount
                val cm = record.conMotion + cmCount

                practiceDao.insertOrUpdate(PracticeRecord(scale,arp,oct,solid,broke, cm, getCurrentDateTime()))

            }else{
                practiceDao.insertOrUpdate(PracticeRecord(scaleCount,arpCount,octCount,solidCount,brokenCount, cmCount, getCurrentDateTime()))
            }

        }
    }

    fun loadRecord(){
        viewModelScope.launch{

        }
    }

    fun updatedSavedProgress(key : Long, title : String, routine: Array<PracticeSave>, inProgress: Array<PracticeSave>, total : Int, date : Date ) {
        viewModelScope.launch {
            val num = progress.value
            if(num != null){
                practiceDao.update(SavedRoutine(key,title,routine,inProgress,num,total, date))
            }
        }
    }

    fun handleFav(){
        viewModelScope.launch {
            val key =item.value!!.root.strName+item.value!!.scale+item.value!!.tech
            val fav = practiceDao.selectFavourite(key)
            if(fav != key){
                practiceDao.insert(Favourites(key, item.value!!.root.strName,item.value!!.scale,item.value!!.tech.strName, true))
                _isFav.value = true
            }
            else{
                practiceDao.deleteFavourite(key)
                _isFav.value  = false
            }
        }
    }
}