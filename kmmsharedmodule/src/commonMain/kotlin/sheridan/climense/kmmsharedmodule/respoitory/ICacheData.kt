package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.model.Routine

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
interface ICacheData {
    //PracticeSession
    fun insertPracticeSession(practiceSession: PracticeSession)
    fun updatePracticeSession(practiceSession: PracticeSession)
    fun getAllPracticeSessions(): List<PracticeSession>
    fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>
    fun deletePracticeSession(date: Long)

    //Favourites
    fun insertFavourite(practice: Practice)
    fun getAllFavourites(): List<Practice>
    fun deleteFavourite(key: Long)

    //Routines
    fun insertRoutine(routine: Routine)
    fun getAllRoutines(): List<Routine>
    fun deleteRoutine(key: Long)
}