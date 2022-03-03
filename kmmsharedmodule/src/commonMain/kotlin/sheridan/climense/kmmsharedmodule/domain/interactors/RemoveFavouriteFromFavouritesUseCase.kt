package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseIn
import sheridan.climense.kmmsharedmodule.model.PracticeSession
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */

class RemoveFavouriteFromFavouritesUseCase(private val repository : IRepository):
    UseCaseIn<Long> {
    override fun execute(param: Long): Flow<Unit> =
        repository.removeFavourites(param)
}




