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
interface IRepository {
    //Practice Session
    fun insertPracticeSession(practiceSession: PracticeSession) : Flow<Unit>
    fun updatePracticeSession(practiceSession: PracticeSession)
    fun getAllPracticeSessions(): List<PracticeSession>
    fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>
    fun deletePracticeSession(date: Long)

    //Routines
    fun insertRoutine(routine: Routine) : Flow<Unit>
    fun getAllRoutinesInfo() : Flow<List<RoutineInfo>>
    fun removeRoutine(id : Long) : Flow<Unit>
    fun getAllRoutineItemsById(id : Long) : Flow<List<Practice>>

    //Favourites
    fun insertFavourites(practice: Practice) : Flow<Unit>
    fun removeFavourites(id : Long) : Flow<Unit>
    fun getAllFavourites():Flow<List<Practice>>
}