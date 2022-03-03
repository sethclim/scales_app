package sheridan.climense.kmmsharedmodule.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.AddFavouriteToFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.GetAllFavouritesUseCase
import sheridan.climense.kmmsharedmodule.domain.interactors.RemoveFavouriteFromFavouritesUseCase
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

val repositoryModule = module {
    single<IRepository>{ RepositoryImp(get()) }
    single<ICacheData> { CacheDataImp(get()) }
}

val domainModule = module {
    single { RoutineGenerator() }
}

val useCasesModule: Module = module {
    factory { AddPracticeSessionToPracticeRecordUseCase(get()) }
    factory { AddFavouriteToFavouritesUseCase(get()) }
    factory { RemoveFavouriteFromFavouritesUseCase(get()) }
    factory { GetAllFavouritesUseCase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}