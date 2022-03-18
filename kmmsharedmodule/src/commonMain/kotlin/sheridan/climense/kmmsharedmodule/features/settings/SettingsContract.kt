package sheridan.climense.kmmsharedmodule.features.settings

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo

/**
scales_app2
sheridan.climense.kmmsharedmodule.features.settings
created by: seth
on: 2022-03-17           */
class SettingsContract {
    sealed class Event : UiEvent {
        object RemoveAllFavourites : Event()
        object RemoveAllHistory : Event()
        object RemoveALlSavedRoutines : Event()
        object RemoveALlData : Event()
    }

    data class State(
        val blank : Long

    ) : UiState

    sealed class Effect : UiEffect {
        object AllFavouritesRemoved : Effect()
        object AllHistoryRemoved : Effect()
        object AllSavedRoutinesRemoved : Effect()
    }
}