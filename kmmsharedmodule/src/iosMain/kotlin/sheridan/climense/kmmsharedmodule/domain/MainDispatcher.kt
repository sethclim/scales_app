package sheridan.climense.kmmsharedmodule.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock

/**
 *scales_app2
createdbyseth*
studentID:991599894
 *on2022-03-02
 */
actual class MainDispatcher {
    actual val dispatcher: CoroutineDispatcher = MainLoopDispatcher
}

object MainLoopDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock { block.run() }
    }
}