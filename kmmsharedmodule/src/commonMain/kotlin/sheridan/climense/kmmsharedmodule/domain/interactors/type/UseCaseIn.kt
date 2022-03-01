package sheridan.climense.kmmsharedmodule.domain.interactors.type

import kotlinx.coroutines.flow.Flow

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */
interface UseCaseIn<in IN> {
    fun execute(param: IN): Flow<Unit>
}