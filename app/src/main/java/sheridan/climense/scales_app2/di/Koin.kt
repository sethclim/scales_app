package sheridan.climense.scales_app2.di

import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.viewmodels.RoutineCreatorViewModel

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */
val viewModelModule = module {
    single { RoutineCreatorViewModel(get()) }
}