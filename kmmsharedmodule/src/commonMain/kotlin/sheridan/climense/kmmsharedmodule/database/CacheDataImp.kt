package sheridan.climense.kmmsharedmodule.database

import co.touchlab.kermit.Logger
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter
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

    override fun getAllRoutines(): List<Routine>{
        val routines =  dbQuery.getAllRoutines().executeAsList()

        val listRoutines = mutableListOf<Routine>()

//        for(item in routines)
//        {
//            val routineItems = dbQuery.getRoutineItemsById(item.id).executeAsList()
//            val practiceItems = dbQuery.getRoutineItemsById(item.id).executeAsList()
//            listRoutines.add(Routine(item.id,item.title, item.date, routineItems, practiceItems))
//        }

        return listRoutines
    }

    override fun deleteRoutine(key: Long){
        dbQuery.deleteRoutine(key)
    }

}