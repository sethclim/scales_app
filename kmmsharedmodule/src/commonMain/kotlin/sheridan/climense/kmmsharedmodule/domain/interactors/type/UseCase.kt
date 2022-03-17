package sheridan.climense.kmmsharedmodule.domain.interactors.type

import kotlinx.coroutines.flow.Flow

/**
scales_app2
sheridan.climense.kmmsharedmodule.domain.interactors.type
created by: seth
on: 2022-03-17           */
interface UseCase {
    fun execute(): Flow<Unit>
}