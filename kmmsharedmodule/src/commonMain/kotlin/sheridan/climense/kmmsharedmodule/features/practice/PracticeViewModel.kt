package sheridan.climense.kmmsharedmodule.features.practice

import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import co.touchlab.kermit.Logger

import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.AddFavouriteToFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveFavouriteFromFavouritesUseCase
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.model.TechTypes
import kotlin.random.Random

/**
    scales_app2
    createdbyseth
    studentID:991599894
    on2022-02-28
 */

data class PracticeContainer(val id : Long, val root : String, val scale : String, val tech : TechTypes, val isFav : Boolean)

class PracticeViewModel : BaseViewModel<PracticeContract.Event, PracticeContract.State, UiEffect>() {

    private val generator: RoutineGenerator by inject()
    private val addPracticeSessionToPracticeRecordUseCase: AddPracticeSessionToPracticeRecordUseCase by inject()
    private val addFavouriteToFavouritesUseCase: AddFavouriteToFavouritesUseCase by inject()
    private val removeFavouriteFromFavouritesUseCase: RemoveFavouriteFromFavouritesUseCase by inject()
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase by inject()

    private var practiceArray : MutableList<PracticeContainer> = mutableListOf()
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

        practiceArray = checkForFavourites(generator.routine, this.uiState.value.favourites)

        startLength = practiceArray.size
        setState{copy(pMax = practiceArray.size)}
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
        if(practiceArray.size > 0)
        {
            val index = Random.nextInt(0, practiceArray.size)
            item = practiceArray[index]

            practiceSession = updateSession(item.tech)

            practiceArray.removeAt(index)

            setState{copy(practice = BasicUiState.Success(item))}
            setState{copy(progress = startLength!! - practiceArray.size)}

            if(practiceArray.size == 0){
                setState{copy(done = true)}
            }
        }
    }

    private fun updateSession(item : TechTypes) : PracticeSession{
        var scaleCount : Long = practiceSession.scale
        var arpCount : Long = practiceSession.arps
        var solidCount : Long = practiceSession.solid
        var brokenCount : Long = practiceSession.broken
        var octCount : Long = practiceSession.oct
        var cmCount : Long = practiceSession.conMotion

        when(item){
            TechTypes.Scale -> scaleCount += 1
            TechTypes.Arp -> arpCount += 1
            TechTypes.Solid -> solidCount += 1
            TechTypes.Broken -> brokenCount += 1
            TechTypes.Oct -> octCount += 1
            TechTypes.CM -> cmCount += 1
        }

        return PracticeSession(0L, scaleCount,arpCount,solidCount,brokenCount,octCount,cmCount)
    }

    private fun checkForFavourites(list : List<Practice>, favs : List<Practice>) : MutableList<PracticeContainer>
    {
        val conList : MutableList<PracticeContainer> = mutableListOf()

        for(item in list){
            conList.add(PracticeContainer(item.id, item.root, item.scale, item.tech, favs.contains(item)))
        }

        return conList
    }

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