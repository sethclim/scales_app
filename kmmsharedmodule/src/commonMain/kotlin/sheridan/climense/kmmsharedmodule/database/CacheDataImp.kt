package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.ColumnAdapter
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.domain.model.Routine
import sheridan.climense.kmmsharedmodule.domain.model.TechTypes
import sheridan.climense.kmmsharedmodule.respoitory.ICacheData


/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class CacheDataImp(databaseDriverFactory: DatabaseDriverFactory) : ICacheData {

    private val techAdapter = object : ColumnAdapter<TechTypes, String> {
        override fun decode(databaseValue: String): TechTypes = when (databaseValue) {
            "scale" -> TechTypes.Scale
            "arp" -> TechTypes.Arp
            "broken" -> TechTypes.Broken
            "cm" -> TechTypes.CM
            "oct" -> TechTypes.Oct
            "solid" -> TechTypes.Solid
            else -> TechTypes.Scale
        }

        override fun encode(value: TechTypes): String = when (value) {
            TechTypes.Scale -> "scale"
            TechTypes.Arp -> "arp"
            TechTypes.Broken-> "broken"
            TechTypes.CM -> "cm"
            TechTypes.Oct -> "oct"
            TechTypes.Solid -> "solid"
        }
    }

    private val database =
       AppDatabase.invoke(
            databaseDriverFactory.createDriver(),
            FavouritesAdapter = Favourites.Adapter(
                techAdapter = techAdapter
            )
        )
    private val dbQuery = database.appDatabaseQueries

    //PracticeSession
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

    //Favourites
    override fun insertFavourite(practice: Practice){
        dbQuery.insertFavourite(
            0,
            practice.root,
            practice.scale,
            practice.tech
        )
    }

    override fun getAllFavourites(): List<Practice>{
        return dbQuery.getAllFavourites(::Practice).executeAsList()
    }

    override fun deleteFavourite(key: Long){}

    //Routines
    override fun insertRoutine(routine: Routine){
        dbQuery.insertRoutine(
            0,
            routine.title,
            routine.date
        )
    }

    override fun getAllRoutines(): List<Routine>{
        return dbQuery.getAllRoutines(::Routine).executeAsList()
    }

    override fun deleteRoutine(id: Long){
        dbQuery.deleteRoutine(id)
    }

}