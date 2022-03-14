package sheridan.climense.kmmsharedmodule.features.practice

import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.PracticeMediator

import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.AddFavouriteToFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveFavouriteFromFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.PracticeContainer
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType
import kotlin.random.Random

/**
    scales_app2
    createdbyseth
    studentID:991599894
    on2022-02-28
 */

class PracticeViewModel : BaseViewModel<PracticeContract.Event, PracticeContract.State, UiEffect>() {

    private val practiceMediator : PracticeMediator by inject()
    private val addPracticeSessionToPracticeRecordUseCase: AddPracticeSessionToPracticeRecordUseCase by inject()
    private val addFavouriteToFavouritesUseCase: AddFavouriteToFavouritesUseCase by inject()
    private val removeFavouriteFromFavouritesUseCase: RemoveFavouriteFromFavouritesUseCase by inject()
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase by inject()

    private var currentPractice : MutableList<PracticeContainer> = mutableListOf()
    private var startLength : Int? = null

    private lateinit var item : PracticeContainer

    private var practiceSession: PracticeSession = PracticeSession(0,0,0,0,0,0,0)

    init{

        launch(getAllFavouritesUseCase.execute(), { favourites ->
            setState {
                copy(
                    favourites = favourites
                )
            }
        })

        launch {
            practiceMediator.practiceListFlow.collect{ value ->
                //Logger.i{"COLLECT VALUE $value"}
                currentPractice = value.toMutableList()
                startLength = currentPractice.size
                setState{copy(pMax = currentPractice.size)}
                setState{copy(done = false)}
            }
        }
    }

    override fun createInitialState(): PracticeContract.State =
        PracticeContract.State(
            practice = BasicUiState.Idle,
            progress = 0,
            pMax = 0,
            done = false,
            favourites = emptyList()
        )

    override fun handleEvent(event: PracticeContract.Event) {
        when (event) {
            PracticeContract.Event.OnGetScale -> nextScale()
            PracticeContract.Event.SavePracticeSession -> savePracticeSessionToPracticeRecord()
            PracticeContract.Event.AddFavourite -> addFavourite()
            PracticeContract.Event.RemoveFavourite -> removeFavourite()
        }
    }

    private fun nextScale(){
        if(currentPractice.size > 0)
        {
            val index = Random.nextInt(0, currentPractice.size)
            item = currentPractice[index]

            practiceSession = updateSession(item.tech)

            currentPractice.removeAt(index)

            setState{copy(practice = BasicUiState.Success(item))}
            setState{copy(progress = startLength!! - currentPractice.size)}

            if(currentPractice.size == 0){
                setState{copy(done = true)}
//                practiceMediator.clearList()
            }
        }
    }

    private fun updateSession(item : TechType) : PracticeSession{
        var scaleCount : Long = practiceSession.scale
        var arpCount : Long = practiceSession.arps
        var solidCount : Long = practiceSession.solid
        var brokenCount : Long = practiceSession.broken
        var octCount : Long = practiceSession.oct
        var cmCount : Long = practiceSession.conMotion

        when(item){
            TechType.Scale -> scaleCount += 1
            TechType.Arp -> arpCount += 1
            TechType.Solid -> solidCount += 1
            TechType.Broken -> brokenCount += 1
            TechType.Oct -> octCount += 1
            TechType.CM -> cmCount += 1
        }

        return PracticeSession(0L, scaleCount,arpCount,solidCount,brokenCount,octCount,cmCount)
    }

//    private fun checkForFavourites(list : List<Practice>, favs : List<Practice>) : MutableList<PracticeContainer>{
//        val conList : MutableList<PracticeContainer> = mutableListOf()
//
//        for(item in list){
//            conList.add(PracticeContainer(item.id, item.root, item.scale, item.tech, favs.contains(item)))
//        }
//
//        return conList
//    }

    private fun savePracticeSessionToPracticeRecord(){
        launch(addPracticeSessionToPracticeRecordUseCase.execute(practiceSession),{
            setEffect { PracticeContract.Effect.SessionSaved }
        })
    }

    private fun addFavourite(){
        launch(addFavouriteToFavouritesUseCase.execute(Practice(0L, item.root, item.scale, item.tech)),{
            setEffect { PracticeContract.Effect.FavAdded }
        })
    }

    private fun removeFavourite(){
        launch(removeFavouriteFromFavouritesUseCase.execute(item.id),{
            setEffect { PracticeContract.Effect.FavRemoved }
        })
    }
}