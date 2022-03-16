package sheridan.climense.kmmsharedmodule.features.history

import co.touchlab.kermit.Logger
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.interactors.GetPracticeSessionsForDateRange
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoField

/**
scales_app2
sheridan.climense.kmmsharedmodule.features.history
created by: seth
on: 2022-03-15           */

class HistoryViewModel : BaseViewModel<HistoryContract.Event, HistoryContract.State, UiEffect>()  {

    private val getPracticeSessionsForDateRange : GetPracticeSessionsForDateRange by inject()

    override fun createInitialState(): HistoryContract.State =
        HistoryContract.State(
            practiceHistory = BasicUiState.Idle
        )

    override fun handleEvent(event: HistoryContract.Event) {
        when(event){
            is HistoryContract.Event.SetNewRangeEvent -> getHistoryForDateRange(event.startDate, event.endDate)
        }
    }

    private fun getHistoryForDateRange(startDate : Long, endDate : Long){
        launch(getPracticeSessionsForDateRange.execute(Pair(startDate, endDate)),{ sessions ->
            setState { copy(practiceHistory = BasicUiState.Success(sessions)) }
        })
    }
}