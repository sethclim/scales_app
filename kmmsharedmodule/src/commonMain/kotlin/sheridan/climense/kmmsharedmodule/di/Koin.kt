package sheridan.climense.kmmsharedmodule.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import sheridan.climense.kmmsharedmodule.database.CacheDataImp
import sheridan.climense.kmmsharedmodule.respoitory.ICacheData
import sheridan.climense.kmmsharedmodule.respoitory.IRepository
import sheridan.climense.kmmsharedmodule.respoitory.RepositoryImp

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            platformModule()
        )
    }

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get()) }
    single<ICacheData> { CacheDataImp(get()) }
}