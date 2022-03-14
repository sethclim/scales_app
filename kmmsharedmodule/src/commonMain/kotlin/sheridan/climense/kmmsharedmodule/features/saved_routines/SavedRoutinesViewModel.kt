package sheridan.climense.kmmsharedmodule.features.saved_routines

import co.touchlab.kermit.Logger
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.PracticeMediator
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllRoutineItemsUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllSavedRoutinesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveSavedRoutineFromRoutinesUseCase
import sheridan.climense.kmmsharedmodule.domain.model.Practice

/**
scales_app2
sheridan.climense.kmmsharedmodule.features.favourites
created by: seth
on: 2022-03-06           */

class SavedRoutinesViewModel : BaseViewModel<SavedRoutinesContract.Event, SavedRoutinesContract.State, UiEffect>() {
    private val practiceMediator : PracticeMediator by inject()
    private val generator : RoutineGenerator by inject()
    private val getAllSavedRoutinesUseCase: GetAllSavedRoutinesUseCase by inject()
    private val getAllSavedRoutineItemsUseCase : GetAllRoutineItemsUseCase by inject()
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase by inject()
    private val removeSavedRoutineFromRoutinesUseCase : RemoveSavedRoutineFromRoutinesUseCase by inject()

    private var favourites : List<Practice> = emptyList()

    init {
        getSavedRoutines()
        getFavourites()
    }

    override fun createInitialState(): SavedRoutinesContract.State =
      SavedRoutinesContract.State(
          savedRoutines = BasicUiState.Idle,
          favourites = BasicUiState.Idle
      )

    override fun handleEvent(event: SavedRoutinesContract.Event) {
        when(event){
            is SavedRoutinesContract.Event.StartSavedRoutine -> startSavedRoutine(event.routineID)
            is SavedRoutinesContract.Event.RemoveSavedRoutine -> removeSavedRoutine(event.routineID)
            SavedRoutinesContract.Event.StartFavourites -> startFavouritesPractice()
            SavedRoutinesContract.Event.GetSavedRoutines -> getSavedRoutines()
            SavedRoutinesContract.Event.GetFavourites -> getFavourites()
        }
    }

    private fun getSavedRoutines(){
        setState { copy(savedRoutines = BasicUiState.Loading) }
        launch(getAllSavedRoutinesUseCase.execute(), { routines ->
            Logger.i{"Routines Saved $routines"}
            setState {
                copy(
                    savedRoutines =
                    if (routines.isEmpty())
                        BasicUiState.Empty
                    else{
                        BasicUiState.Success(routines)
                    }
                )
            }
        }, {
            setState { copy(savedRoutines = BasicUiState.Error()) }
        })
    }

    private fun getFavourites(){
        launch(getAllFavouritesUseCase.execute(), { favourites ->
            this.favourites = favourites
        })
    }

    private fun startSavedRoutine(routineID : Long){
        launch(getAllSavedRoutineItemsUseCase.execute(routineID), { routineItems ->
            val practiceRoutine = generator.generate(routineItems)
            launch{
                practiceMediator.setPracticeList(practiceRoutine.toList())
            }
        })
        setEffect { SavedRoutinesContract.Effect.SavedRoutineSetup }
    }

    private fun removeSavedRoutine(routineID: Long){
        launch(removeSavedRoutineFromRoutinesUseCase.execute(routineID), {
           setEffect { SavedRoutinesContract.Effect.SavedRoutinesUpdated }
        })
    }

    private fun startFavouritesPractice(){
        if(favourites.isNotEmpty()){
            setEffect { SavedRoutinesContract.Effect.FavouritesExist }
            val practiceRoutine = generator.generate(favourites)
            launch{
                practiceMediator.setPracticeList(practiceRoutine.toList())
            }
        }
        else{
            setEffect { SavedRoutinesContract.Effect.FavouritesError }
        }
    }
}