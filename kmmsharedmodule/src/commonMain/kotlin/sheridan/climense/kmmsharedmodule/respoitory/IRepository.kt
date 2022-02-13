package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
interface IRepository {
    fun insertPracticeSession(practiceSession: PracticeSession)
    fun updatePracticeSession(practiceSession: PracticeSession)
    fun getAllPracticeSessions(): List<PracticeSession>
    fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>
    fun deletePracticeSession(date: Long)
}