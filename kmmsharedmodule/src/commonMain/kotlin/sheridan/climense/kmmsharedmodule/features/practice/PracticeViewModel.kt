package sheridan.climense.kmmsharedmodule.features.practice

import co.touchlab.kermit.Logger
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.PracticeMediator

import sheridan.climense.kmmsharedmodule.domain.interactors.AddFavouriteToFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveFavouriteFromFavouritesUseCase
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

    private var currentPractice : MutableList<PracticeContainer> = mutableListOf()
    private var startLength : Int? = null

    private var practiceSession: PracticeSession = PracticeSession(0,0,0,0,0,0,0)

    private var todayDate : Long = 0L

    init{
        launch {
            practiceMediator.practiceListFlow.collect{ value ->
                //Logger.i{"COLLECT VALUE $value"}
                currentPractice = value.toMutableList()
                startLength = currentPractice.size
                setState{copy(practice = BasicUiState.Idle ) }
                setState{copy(pMax = currentPractice.size)}
                setState{copy(progress = 0)}
                setState{copy(done = false)}
            }
        }
    }

    override fun createInitialState(): PracticeContract.State =
        PracticeContract.State(
            practice = BasicUiState.Idle,
            progress = 0,
            pMax = 0,
            done = false
        )

    override fun handleEvent(event: PracticeContract.Event) {
        when (event) {
            PracticeContract.Event.OnGetScale -> nextScale()
            PracticeContract.Event.SavePracticeSession -> savePracticeSessionToPracticeRecord()
            PracticeContract.Event.AddFavourite -> addFavourite()
            PracticeContract.Event.RemoveFavourite -> removeFavourite()
            is PracticeContract.Event.SetTodayDate -> updateDate(event.date)
        }
    }

    private fun nextScale(){
        if(currentPractice.size > 0)
        {
            val index = Random.nextInt(0, currentPractice.size)
            val item = currentPractice[index]

            practiceSession = updateSession(item.tech)
            practiceSession.date = todayDate

            currentPractice.removeAt(index)

            setState{copy(practice = BasicUiState.Success(item))}
            setState{copy(progress = startLength!! - currentPractice.size)}

            if(currentPractice.size == 0){
                setState{copy(done = true)}
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

    private fun savePracticeSessionToPracticeRecord(){
        launch(addPracticeSessionToPracticeRecordUseCase.execute(practiceSession),{
            setEffect { PracticeContract.Effect.SessionSaved }
        })
    }

    private fun addFavourite(){
        val practice = uiState.value.practice.accessData()

        if(practice != null){
            launch(addFavouriteToFavouritesUseCase.execute(practice.toPractice()), {
                setEffect { PracticeContract.Effect.FavAdded }
                setState { copy(practice = BasicUiState.Success(PracticeContainer(practice.id, practice.root, practice.scale, practice.tech, isFav =  true))) }
            })
        }
    }

    private fun removeFavourite(){
        val data = uiState.value.practice.accessData()
        if(data !=null) {
            launch(removeFavouriteFromFavouritesUseCase.execute(data.id), {
                setEffect { PracticeContract.Effect.FavRemoved }
            })
        }
    }

    private fun updateDate(date : Long){
        todayDate = date
    }
}