package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseOut
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.Routine
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors
created by: seth
on: 2022-03-06           */
class GetAllSavedRoutinesUseCase(private val repository: IRepository
): UseCaseOut<List<RoutineInfo>> {
    override fun execute(): Flow<List<RoutineInfo>> = repository.getAllRoutinesInfo()
}