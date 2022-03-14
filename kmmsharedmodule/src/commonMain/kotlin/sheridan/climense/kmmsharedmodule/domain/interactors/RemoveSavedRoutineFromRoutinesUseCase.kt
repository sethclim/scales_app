package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseIn
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors
created by: seth
on: 2022-03-07           */

class RemoveSavedRoutineFromRoutinesUseCase(private val repository : IRepository):
    UseCaseIn<Long> {
    override fun execute(param: Long): Flow<Unit> =
        repository.removeRoutine(param)
}
