package sheridan.climense.kmmsharedmodule.domain

import kotlinx.coroutines.CoroutineDispatcher

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
expect class MainDispatcher() {
    val dispatcher: CoroutineDispatcher
}