package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseInOut
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors
created by: seth
on: 2022-03-15           */

class GetPracticeSessionsForDateRange(private val repository: IRepository
): UseCaseInOut<Pair<Long, Long>, List<PracticeSession>> {
    override fun execute(param: Pair<Long, Long>): Flow<List<PracticeSession>> = repository.getPracticeSessionRange(param.first, param.second)
}