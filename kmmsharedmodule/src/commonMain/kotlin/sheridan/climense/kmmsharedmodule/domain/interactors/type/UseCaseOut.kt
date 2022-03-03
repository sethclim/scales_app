package sheridan.climense.kmmsharedmodule.domain.interactors.type

import kotlinx.coroutines.flow.Flow

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
interface UseCaseOut<out OUT> {
    fun execute(): Flow<OUT>
}