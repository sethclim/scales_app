package sheridan.climense.kmmsharedmodule.features.saved_routines

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo

/**
scales_app2
sheridan.climense.kmmsharedmodule.features.favourites
created by: seth
on: 2022-03-06           */

class SavedRoutinesContract {
    sealed class Event : UiEvent {
        data class StartSavedRoutine(val routineID : Long) : Event()
        data class RemoveSavedRoutine(val routineID : Long) : Event()
        object StartFavourites : Event()
        object GetSavedRoutines : Event()
        object GetFavourites : Event()
    }

    data class State(
        val savedRoutines: BasicUiState<List<RoutineInfo>>,
        val favourites: BasicUiState<List<Practice>>
    ) : UiState

    sealed class Effect : UiEffect {
        object FavouritesExist : Effect()
        object FavouritesError : Effect()
        object SavedRoutinesUpdated : Effect()
        object SavedRoutineSetup : Effect()
    }
}

