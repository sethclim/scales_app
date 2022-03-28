package sheridan.climense.kmmsharedmodule

import co.touchlab.kermit.Logger
import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.database.DatabaseDriverFactory
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession

class Greeting {
    fun greeting(): String {
        Logger.i { "Hello World" }

        return "Hello, ${Platform().platform}!"
    }
}