package sheridan.climense.kmmsharedmodule.database

import co.touchlab.kermit.Logger
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter
import sheridan.climense.kmmsharedmodule.domain.model.*
import sheridan.climense.kmmsharedmodule.domain.model.Routine
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType
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
            FavouritesAdapter = Favourites.Adapter(
                techAdapter = EnumColumnAdapter(),
                rootAdapter = EnumColumnAdapter(),
                scaleAdapter = EnumColumnAdapter()
            ),
           RoutineItemsAdapter = RoutineItems.Adapter(
               techAdapter = EnumColumnAdapter(),
               rootAdapter = EnumColumnAdapter(),
               scaleAdapter = EnumColumnAdapter()
           ),
           InProgressItemsAdapter = InProgressItems.Adapter(
               techAdapter = EnumColumnAdapter(),
               rootAdapter = EnumColumnAdapter(),
               scaleAdapter = EnumColumnAdapter()
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

    override fun deleteAllPracticeSessions() {
        dbQuery.deleteAllPracticeSessions()
    }

    //Favourites
    override fun insertFavourite(practice: Practice){
        dbQuery.insertFavourite(
            key= null,
            practice.root,
            practice.scale,
            practice.tech
        )
    }

    override fun getAllFavourites(): List<Practice>{
        return dbQuery.getAllFavourites(::Practice).executeAsList()
    }

    override fun deleteFavourite(key: Long){}

    override fun deleteAllFavourites() {
        dbQuery.deleteAllFavourites()
    }

    //Routines
    override fun insertRoutine(routine: Routine){

        Logger.i{"Insert Routine"}

        dbQuery.insertRoutine(
            id = null,
            routine.title,
            routine.date
        )

        val id : Long = dbQuery.lastInsertRowIdRoutines().executeAsOne()

        dbQuery.transaction {
            routine.routineItems.forEach{ item ->
                Logger.i{"Insert Routine Item: $item"}
                dbQuery.insertRoutineItem(
                    id = null,
                    id,
                    item.root,
                    item.scale,
                    item.tech
                )
            }
        }
    }

    override fun getAllRoutines(): List<RoutineInfo>{
        return dbQuery.getAllRoutines(::RoutineInfo).executeAsList()
    }

    override fun getAllRoutineItemsById(key : Long): List<Practice>{
        return dbQuery.getRoutineItemsById(key, ::mapRoutineItem).executeAsList()
    }

    override fun deleteRoutine(key: Long){
        Logger.i{"Key $key"}
        dbQuery.deleteRoutine(key)
        dbQuery.deleteRoutineItems(key)
    }

    override fun deleteAllRoutines() {
        dbQuery.deleteAllRoutineInfo()
        dbQuery.deleteAllRoutineItems()
    }

    private fun mapRoutineItem(
        id: Long,
        routineRef: Long,
        rootType: RootType,
        scaleType: ScaleType,
        techType: TechType
    ): Practice = Practice(id, rootType, scaleType, techType)

}