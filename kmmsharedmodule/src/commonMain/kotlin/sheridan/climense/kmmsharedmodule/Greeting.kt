package sheridan.climense.kmmsharedmodule

import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.database.DatabaseDriverFactory
import sheridan.climense.kmmsharedmodule.model.Practice

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    fun getData(databaseDriverFactory: DatabaseDriverFactory) : List<Practice>{
       return RepositoryImp(CacheDataImp(databaseDriverFactory)).getPractice();
    }
}