package sheridan.climense.kmmsharedmodule.features.creator

import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.base.mvi.UiEvent
import sheridan.climense.kmmsharedmodule.base.mvi.UiState
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-03
 */

interface CreatorContract {

    sealed class Event : UiEvent {
        data class SetScaleEvent(val scaleType: ScaleType,val addRemove : Boolean) : Event()
        data class SetTechEvent(val techType: TechType,val addRemove : Boolean) : Event()

        data class SaveRoutine(val name : String) : Event()
        data class SetRootsEvent(val rootType: RootType,val addRemove : Boolean) : Event()
        object ConfirmRootsEvent : Event()
        data class ToggleCustomRoots(val onOff : Boolean) : Event()
    }

    data class State(
        val scalesCheckBoxes: BasicUiState<MutableMap<ScaleType, Boolean>>,
        val techCheckBoxes: BasicUiState<MutableMap<TechType, Boolean>>,
        val rootCheckBoxes: BasicUiState<MutableMap<RootType, Boolean>>,
        val customRootState: BasicUiState<Boolean>
    ) : UiState

    sealed class Effect : UiEffect {
        object RoutineSaved : Effect()
    }
}