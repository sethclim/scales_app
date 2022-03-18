package sheridan.climense.kmmsharedmodule.features.settings

import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveAllFavourites
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveAllHistory
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveAllSavedRoutines


/**
scales_app2
sheridan.climense.kmmsharedmodule.features.settings
created by: seth
on: 2022-03-17           */

class SettingsViewModel : BaseViewModel<SettingsContract.Event, SettingsContract.State, UiEffect>() {
    private val removeAllFavourites : RemoveAllFavourites by inject()
    private val removeAllHistory : RemoveAllHistory by inject()
    private val removeAllSavedRoutines : RemoveAllSavedRoutines by inject()

    override fun createInitialState(): SettingsContract.State =
        SettingsContract.State(
            blank = 0L
        )


    override fun handleEvent(event: SettingsContract.Event) {
        when(event){
            SettingsContract.Event.RemoveAllFavourites -> handleRemoveAllFavourites()
            SettingsContract.Event.RemoveAllHistory -> handleRemoveAllHistory()
            SettingsContract.Event.RemoveALlSavedRoutines -> handleRemoveAllSavedRoutines()
            SettingsContract.Event.RemoveALlData ->removeAllData()
        }
    }

    private fun removeAllData(){
        handleRemoveAllFavourites()
        handleRemoveAllHistory()
        handleRemoveAllSavedRoutines()
    }

    private fun handleRemoveAllFavourites(){
        launch( removeAllFavourites.execute(),{
                setEffect { SettingsContract.Effect.AllFavouritesRemoved }
            }
        )
    }

    private fun handleRemoveAllHistory(){
        launch( removeAllHistory.execute(),{
                setEffect { SettingsContract.Effect.AllHistoryRemoved }
            }
        )
    }

    private fun handleRemoveAllSavedRoutines(){
        launch( removeAllSavedRoutines.execute(),{
                setEffect { SettingsContract.Effect.AllSavedRoutinesRemoved }
            }
        )
    }
}