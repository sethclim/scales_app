package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.database.RoutineItems
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseInOut
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseOut
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors
created by: seth
on: 2022-03-07           */

class GetAllRoutineItemsUseCase(private val repository: IRepository
): UseCaseInOut<Long, List<Practice>> {
    override fun execute(param: Long): Flow<List<Practice>> = repository.getAllRoutineItemsById(param)
}