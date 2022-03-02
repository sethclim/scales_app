package sheridan.climense.kmmsharedmodule.features.practice

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.model.Practice

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
interface PracticeContract {
    sealed class Event : UiEvent {
        object OnGetScale : Event()
    }

    data class State(
        val practice: BasicUiState<Practice>,
        val progress: Int,
        val pMax: Int,
        val done: Boolean
    ) : UiState
}