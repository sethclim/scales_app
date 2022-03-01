package sheridan.climense.kmmsharedmodule.respoitory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.model.Routine

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class RepositoryImp(private val cacheData: ICacheData,) : IRepository {
    //Session
    override fun insertPracticeSession(practiceSession: PracticeSession) : Flow<Unit> = flow {
        cacheData.insertPracticeSession(practiceSession)
        emit(Unit)
    }
    override fun updatePracticeSession(practiceSession: PracticeSession){ cacheData.updatePracticeSession(practiceSession) }
    override fun getAllPracticeSessions(): List<PracticeSession> { return cacheData.getAllPracticeSessions() }
    override fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>{ return cacheData.getPracticeSessionRange(startDate, endDate) }
    override fun deletePracticeSession(date: Long) { cacheData.deletePracticeSession(date) }

    //Routine
    override fun insertRoutine(routine: Routine) : Flow<Unit> = flow{
        cacheData.insertRoutine(routine)
        emit(Unit)
    }
}
