package sheridan.climense.kmmsharedmodule.respoitory

import sheridan.climense.kmmsharedmodule.model.PracticeSession

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class RepositoryImp(private val cacheData: ICacheData,) : IRepository {
    override fun insertPracticeSession(practiceSession: PracticeSession){ cacheData.insertPracticeSession(practiceSession) }
    override fun updatePracticeSession(practiceSession: PracticeSession){ cacheData.updatePracticeSession(practiceSession) }
    override fun getAllPracticeSessions(): List<PracticeSession> { return cacheData.getAllPracticeSessions() }
    override fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession>{ return cacheData.getPracticeSessionRange(startDate, endDate) }
    override fun deletePracticeSession(date: Long) { cacheData.deletePracticeSession(date) }
}
