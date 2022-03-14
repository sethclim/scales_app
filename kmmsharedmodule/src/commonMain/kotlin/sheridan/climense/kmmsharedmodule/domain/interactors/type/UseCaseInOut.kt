package sheridan.climense.kmmsharedmodule.domain.interactors.type

import kotlinx.coroutines.flow.Flow

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors.type
created by: seth
on: 2022-03-07           */

interface UseCaseInOut<in IN, out OUT> {
    fun execute(param: IN): Flow<OUT>
}