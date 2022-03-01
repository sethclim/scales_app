package sheridan.climense.kmmsharedmodule.respoitory

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.model.Routine

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
interface IRepository {
    //Practice Session
    fun insertPracticeSession(practiceSession: PracticeSession) : Flow<Unit>
    fun updatePracticeSession(practiceSession: PracticeSession)
    fun getAllPracticeSessions(): List<PracticeSession>
    fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>
    fun deletePracticeSession(date: Long)


    //Routines
    fun insertRoutine(routine: Routine) : Flow<Unit>

}