package sheridan.climense.kmmsharedmodule.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.domain.RoutineGenerator
import sheridan.climense.kmmsharedmodule.domain.interactors.AddPracticeSessionToPracticeRecordUseCase
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
            platformModule()
        )
    }

val repositoryModule = module {
    single<IRepository>(named("repo")) { RepositoryImp(get()) }
    single<ICacheData> { CacheDataImp(get()) }
}

val domainModule = module {
    single(named("generator")) { RoutineGenerator() }
}

val useCasesModule: Module = module {
    factory { AddPracticeSessionToPracticeRecordUseCase(get()) }
}