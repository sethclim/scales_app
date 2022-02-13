package sheridan.climense.kmmsharedmodule.database

import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.model.TechTypes
import sheridan.climense.kmmsharedmodule.respoitory.ICacheData



/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class CacheDataImp(databaseDriverFactory: DatabaseDriverFactory) : ICacheData {

    private val database =
       AppDatabase.invoke(
            databaseDriverFactory.createDriver(),
        )
    private val dbQuery = database.appDatabaseQueries


    override fun insertPracticeSession(practiceSession: PracticeSession){
        dbQuery.insertPracticeSession(practiceSession.date,
                                      practiceSession.scale,
                                      practiceSession.arps,
                                      practiceSession.oct,
                                      practiceSession.solid,
                                      practiceSession.broken,
                                      practiceSession.conMotion )
    }

    override fun updatePracticeSession(practiceSession: PracticeSession){
        dbQuery.updatePracticeSession(
            practiceSession.date,
            practiceSession.scale,
            practiceSession.arps,
            practiceSession.oct,
            practiceSession.solid,
            practiceSession.broken,
            practiceSession.conMotion
        )
    }

    override fun getAllPracticeSessions(): List<PracticeSession> {
        return dbQuery.getAllPracticeSessions(::PracticeSession).executeAsList()
    }


    override fun getPracticeSessionRange(startDate : Long, endDate : Long): List<PracticeSession> {
        return dbQuery.getPracticeSeesionRange(startDate,endDate,::PracticeSession).executeAsList()
    }

    override fun deletePracticeSession(date: Long) {
        dbQuery.deletePracticeSession(date)
    }

}