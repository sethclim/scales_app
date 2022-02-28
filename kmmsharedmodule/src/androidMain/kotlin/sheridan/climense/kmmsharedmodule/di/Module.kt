package sheridan.climense.kmmsharedmodule.di

import org.koin.core.module.Module
import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.database.DatabaseDriverFactory

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */
actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(get()) }
//    single { MainDispatcher() }
}

