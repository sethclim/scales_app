package sheridan.climense.kmmsharedmodule.respoitory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun getAllRoutinesInfo() : Flow<List<RoutineInfo>> = flow{
        emit(cacheData.getAllRoutines())
    }

    override fun removeRoutine(id : Long) : Flow<Unit> = flow{
        cacheData.deleteRoutine(key = id)
        emit(Unit)
    }

    override fun getAllRoutineItemsById(id : Long) : Flow<List<Practice>> = flow{
        cacheData.getAllRoutineItemsById(key = id)
    }

    //Favourites
    override fun insertFavourites(practice: Practice) : Flow<Unit> = flow{
        cacheData.insertFavourite(practice)
    }
    override fun removeFavourites(id : Long) : Flow<Unit> = flow{
        cacheData.deleteFavourite(id)
    }
    override fun getAllFavourites():Flow<List<Practice>> = flow{
        emit(cacheData.getAllFavourites())
    }

}
