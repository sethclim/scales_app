package sheridan.climense.kmmsharedmodule.database

import sheridan.climense.kmmsharedmodule.model.Practice
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

    override fun getAllPractice(): List<Practice> {
        return listOf(Practice("C", "Maj",TechTypes.Scale))
    }

}