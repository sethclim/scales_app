package sheridan.climense.kmmsharedmodule.viewmodels

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.logger.Logger
import org.koin.core.qualifier.named
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
class PracticeViewModel : KoinComponent {

    private val generator: RoutineGenerator by inject(named("generator"))
    private val addPracticeSessionToPracticeRecordUseCase: AddPracticeSessionToPracticeRecordUseCase by inject()

    var practiceArray : MutableList<Practice> = mutableListOf()
    var currentScale : Practice? = null

    var progress : Int = 0
    private var startLength : Int? = null
    var done = false

    var scaleCount = 0L
    var arpCount = 0L
    var solidCount = 0L
    var brokenCount = 0L
    var octCount = 0L
    var cmCount = 0L

    fun nextScale(){
        if(practiceArray.size > 0)
        {
            val index = Random.nextInt(0, practiceArray.size)
            val item = practiceArray[index]
            currentScale = item
            practiceArray.removeAt(index)

            progress = startLength!! - practiceArray.size

            if(practiceArray.size == 0) { done = true }
        }
    }

    fun setPracticeArray() : Int {
        practiceArray = generator.routine
        startLength = practiceArray.size

        return practiceArray.size
    }

    fun savePracticeSession(){
        val practiceSession = PracticeSession(0L, scaleCount,arpCount,octCount,solidCount,brokenCount, cmCount)
        addPracticeSessionToPracticeRecordUseCase.execute(practiceSession)
    }

}