package sheridan.climense.kmmsharedmodule.domain.interactors

import kotlinx.coroutines.flow.Flow
import sheridan.climense.kmmsharedmodule.domain.interactors.type.UseCaseOut
import sheridan.climense.kmmsharedmodule.domain.model.Practice
import sheridan.climense.kmmsharedmodule.respoitory.IRepository

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */

class GetAllFavouritesUseCase(
    private val repository: IRepository
): UseCaseOut<List<Practice>> {
    override fun execute(): Flow<List<Practice>> = repository.getAllFavourites()
}