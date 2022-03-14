package sheridan.climense.kmmsharedmodule.features.practice

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.PracticeContainer

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
interface PracticeContract {
    sealed class Event : UiEvent {
        object OnGetScale : Event()
        object SavePracticeSession : Event()
        object AddFavourite : Event()
        object RemoveFavourite : Event()
    }

    data class State(
        val practice: BasicUiState<PracticeContainer>,
        val progress: Int,
        val pMax: Int,
        val done: Boolean,
    ) : UiState

    sealed class Effect : UiEffect {
        object SessionSaved : Effect()
        object FavAdded : Effect()
        object FavRemoved : Effect()
    }
}