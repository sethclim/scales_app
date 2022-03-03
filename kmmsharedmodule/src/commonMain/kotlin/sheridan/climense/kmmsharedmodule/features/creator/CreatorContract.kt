package sheridan.climense.kmmsharedmodule.features.creator

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.ScaleType

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */
interface CreatorContract {
    sealed class Event : UiEvent {
        object AddMaj : Event()
        object RemoveMaj : Event()
        object AddMin : Event()
        object RemoveMin: Event()
        object AddMelMin : Event()
        object RemoveMelMin: Event()
        object AddDim : Event()
        object RemoveDim: Event()
        object AddMaj7 : Event()
        object RemoveMaj7: Event()
        object AddMin7 : Event()
        object RemoveMin7: Event()
        object AddDom7 : Event()
        object RemoveDom7: Event()
        object AddAug : Event()
        object RemoveAug: Event()

    }

    data class State(
        val scalesCheckBoxes: BasicUiState<MutableMap<ScaleType, Boolean>>,
    ) : UiState

    sealed class Effect : UiEffect {
        object SessionSaved : Effect()
        object FavAdded : Effect()
        object FavRemoved : Effect()
    }
}