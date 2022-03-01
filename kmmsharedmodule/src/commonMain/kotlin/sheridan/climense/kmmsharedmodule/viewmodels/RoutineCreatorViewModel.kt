package sheridan.climense.kmmsharedmodule.viewmodels
import org.koin.core.component.KoinComponent
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.model.*
import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp
import java.util.*
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

class RoutineCreatorViewModel: KoinComponent {

    private val repository: IRepository by inject(named("repo"))
    private val generator: RoutineGenerator by inject(named("generator"))

    var routine : Array<Practice> = arrayOf()

    fun generateRoutine(roots : Array<Roots>, scales : Array<Scale>, techs : Array<Tech>) : Boolean{

        routine =  generator.generate(roots, scales, techs)

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