package sheridan.climense.kmmsharedmodule.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.domain.PracticeMediator
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.*
import sheridan.climense.kmmsharedmodule.respoitory.ICacheData
import sheridan.climense.kmmsharedmodule.respoitory.IRepository
import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            domainModule,
            useCasesModule,
            dispatcherModule,
            platformModule()
        )
    }


// IOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<IRepository>{ RepositoryImp(get()) }
    single<ICacheData> { CacheDataImp(get()) }
}

val domainModule = module {
    single { PracticeMediator() }
    single { RoutineGenerator() }
}

val useCasesModule: Module = module {
    factory { AddPracticeSessionToPracticeRecordUseCase(get()) }
    factory { AddFavouriteToFavouritesUseCase(get()) }
    factory { RemoveFavouriteFromFavouritesUseCase(get()) }
    factory { GetAllFavouritesUseCase(get()) }
    factory { AddRoutineToRoutinesUseCase(get()) }
    factory { GetAllSavedRoutinesUseCase(get()) }
    factory { RemoveSavedRoutineFromRoutinesUseCase(get()) }
    factory { GetAllRoutineItemsUseCase(get()) }
    factory { GetPracticeSessionsForDateRange(get()) }
    factory { RemoveAllFavourites(get()) }
    factory { RemoveAllSavedRoutines(get()) }
    factory { RemoveAllHistory(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}