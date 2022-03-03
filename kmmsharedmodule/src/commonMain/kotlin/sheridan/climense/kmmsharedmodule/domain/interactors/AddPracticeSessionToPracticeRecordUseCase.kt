package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseIn
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-02-28
 */

class AddPracticeSessionToPracticeRecordUseCase(private val repository : IRepository):UseCaseIn<PracticeSession> {
    override fun execute(param: PracticeSession): Flow<Unit> =
        repository.insertPracticeSession(param)
}