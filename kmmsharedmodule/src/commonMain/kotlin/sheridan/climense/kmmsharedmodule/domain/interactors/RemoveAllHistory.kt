package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCase
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors
created by: seth
on: 2022-03-17           */
class RemoveAllHistory(private val repository : IRepository):
    UseCase {
    override fun execute(): Flow<Unit> =
        repository.removeAllPracticeSessions()
}