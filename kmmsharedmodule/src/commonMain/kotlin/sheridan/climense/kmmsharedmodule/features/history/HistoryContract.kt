package sheridan.climense.kmmsharedmodule.features.history

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.PracticeContainer
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession

/**
scales_app2
sheridan.climense.kmmsharedmodule.features.history
created by: seth
on: 2022-03-15           */

interface HistoryContract {
    sealed class Event : UiEvent {
        data class SetNewRangeEvent(val startDate : Long, val endDate : Long) : Event()
    }

    data class State(
        val practiceHistory: BasicUiState<List<PracticeSession>>
    ) : UiState

    sealed class Effect : UiEffect {
        object HistoryRefreshed : Effect()
    }
}