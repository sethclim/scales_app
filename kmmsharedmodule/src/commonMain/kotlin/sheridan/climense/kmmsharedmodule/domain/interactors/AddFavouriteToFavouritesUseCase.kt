package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseIn
import sheridan.climense.kmmsharedmodule.model.Practice
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */

class AddFavouriteToFavouritesUseCase(private val repository : IRepository):
    UseCaseIn<Practice> {
    override fun execute(param: Practice): Flow<Unit> =
        repository.insertFavourites(param)
}