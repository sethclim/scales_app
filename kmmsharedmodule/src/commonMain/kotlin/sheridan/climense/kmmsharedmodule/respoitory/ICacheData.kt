package sheridan.climense.kmmsharedmodule.respoitory

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.database.RoutineItems
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.domain.model.Routine
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo

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
    fun deleteAllPracticeSessions()

    //Favourites
    fun insertFavourite(practice: Practice)
    fun getAllFavourites(): List<Practice>
    fun deleteFavourite(key: Long)
    fun deleteAllFavourites()

    //Routines
    fun insertRoutine(routine: Routine)
    fun getAllRoutines(): List<RoutineInfo>
    fun deleteRoutine(key: Long)
    fun getAllRoutineItemsById(key : Long): List<Practice>
    fun deleteAllRoutines()
}