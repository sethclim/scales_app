package sheridan.climense.kmmsharedmodule.viewmodels
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.model.*
import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp
import java.util.*


class RoutineCreatorViewModel(private val repository: RepositoryImp) {

    var routine : Array<Practice> = arrayOf()

    fun generateRoutine(roots : Array<Roots>, scales : Array<Scale>, techs : Array<Tech>) : Boolean{

        routine =  RoutineGenerator.generate(roots, scales, techs)

        return routine.isNotEmpty()
    }

//    fun createFavRoutine() : Boolean{
//        viewModelScope.launch {
//            val pr = practiceDao.getNLFavourites()
//            RoutineGenerator.favourites =  Practice(pr.root, pr.scale, pr.tech)
//        }
//         routine =  RoutineGenerator.generate()
//
//        return routine.isNotEmpty()
//    }

    fun saveRoutine(name : String, date : Date){
        //generateRoutine()
            //val savedRoutine = Routine(0L, name, routine,null, 0,routine.size, date)
            //repository.insertSavedRoutine(savedRoutine)
    }

}