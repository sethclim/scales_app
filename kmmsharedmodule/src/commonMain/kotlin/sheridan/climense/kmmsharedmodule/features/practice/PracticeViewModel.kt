package sheridan.climense.kmmsharedmodule.features.practice

import co.touchlab.kermit.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import sheridan.climense.kmmsharedmodule.base.mvi.BaseViewModel
import sheridan.climense.kmmsharedmodule.base.mvi.BasicUiState
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect

import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import kotlin.random.Random

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */
class PracticeViewModel : BaseViewModel<PracticeContract.Event, PracticeContract.State, UiEffect>() {

    private val generator: RoutineGenerator by inject()
    private val addPracticeSessionToPracticeRecordUseCase: AddPracticeSessionToPracticeRecordUseCase by inject()

    var practiceArray : MutableList<Practice> = mutableListOf()
    var currentScale : Flow<String> = flow{"hello"}

    var progress : Int = 0
    private var startLength : Int? = null
    var done = false

    var scaleCount = 0L
    var arpCount = 0L
    var solidCount = 0L
    var brokenCount = 0L
    var octCount = 0L
    var cmCount = 0L

    init {
        practiceArray = generator.routine
        startLength = practiceArray.size
    }

    override fun createInitialState(): PracticeContract.State =
        PracticeContract.State(
            practice = BasicUiState.Idle,
        )


    override fun handleEvent(event: PracticeContract.Event) {
        when (event) {
            PracticeContract.Event.onGetScale -> nextScale()
        }
    }

    suspend fun get() = coroutineScope  {
        Logger.i{"MSG Running GET"}
        currentScale.collect{value -> Logger.i{"Value $value" }}
    }

    fun nextScale(){
        if(practiceArray.size > 0)
        {
            val index = Random.nextInt(0, practiceArray.size)
            val item = practiceArray[index]
            practiceArray.removeAt(index)

            Logger.i { "item$item" }

            setState{copy(practice = BasicUiState.Success(item.root))}
//
//            progress = startLength!! - practiceArray.size
//
//            if(practiceArray.size == 0) { done = true }
        }
    }

//    fun setPracticeArray() : Int {
//        practiceArray = generator.routine
//        startLength = practiceArray.size
//
//        return practiceArray.size
//    }
//
//    fun savePracticeSession(){
//        val practiceSession = PracticeSession(0L, scaleCount,arpCount,octCount,solidCount,brokenCount, cmCount)
//        addPracticeSessionToPracticeRecordUseCase.execute(practiceSession)
//    }

}