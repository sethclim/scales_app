package sheridan.climense.kmmsharedmodule.database

import com.squareup.sqldelight.ColumnAdapter
import sheridan.climense.kmmsharedmodule.domain.model.*
import sheridan.climense.kmmsharedmodule.domain.model.Routine
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType
import sheridan.climense.kmmsharedmodule.respoitory.ICacheData


/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-12
 */
class CacheDataImp(databaseDriverFactory: DatabaseDriverFactory) : ICacheData {

    private val techAdapter = object : ColumnAdapter<TechType, String> {
        override fun decode(databaseValue: String): TechType = when (databaseValue) {
            "scale" -> TechType.Scale
            "arp" -> TechType.Arp
            "broken" -> TechType.Broken
            "cm" -> TechType.CM
            "oct" -> TechType.Oct
            "solid" -> TechType.Solid
            else -> TechType.Scale
        }

        override fun encode(value: TechType): String = when (value) {
            TechType.Scale -> "scale"
            TechType.Arp -> "arp"
            TechType.Broken-> "broken"
            TechType.CM -> "cm"
            TechType.Oct -> "oct"
            TechType.Solid -> "solid"
        }
    }

    private val rootAdapter = object : ColumnAdapter<RootType, String> {
        override fun decode(databaseValue: String): RootType = when (databaseValue) {
            "C" -> RootType.C
            "C#" -> RootType.Cs
            "D" -> RootType.D
            "D#" -> RootType.Ds
            "E" -> RootType.E
            "F" -> RootType.F
            "F#" -> RootType.Fs
            "G" -> RootType.G
            "G#" -> RootType.Gs
            "A" -> RootType.A
            "A#" -> RootType.As
            "B" -> RootType.B
            else -> RootType.C
        }

        override fun encode(value: RootType): String = when (value) {
            RootType.C -> "C"
            RootType.Cs -> "C#"
            RootType.D -> "D"
            RootType.Ds -> "D#"
            RootType.E ->  "E"
            RootType.F -> "F"
            RootType.Fs -> "F#"
            RootType.G ->  "G"
            RootType.Gs -> "G#"
            RootType.A -> "A"
            RootType.As -> "A#"
            RootType.B -> "B"
        }
    }

    private val database =
       AppDatabase.invoke(
            databaseDriverFactory.createDriver(),
            FavouritesAdapter = Favourites.Adapter(
                techAdapter = techAdapter,
                rootAdapter = rootAdapter
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