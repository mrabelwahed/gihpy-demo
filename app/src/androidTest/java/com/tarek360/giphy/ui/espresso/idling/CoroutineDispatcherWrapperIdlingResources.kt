package com.tarek360.giphy.ui.espresso.idling

import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
class CoroutineDispatcherWrapperIdlingResources(private val dispatcher: CoroutineDispatcher) : CoroutineDispatcher(), Delay {

    @ExperimentalCoroutinesApi
    override fun isDispatchNeeded(context: CoroutineContext): Boolean = dispatcher.isDispatchNeeded(context)

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatcher.dispatch(context, block.withIdlingResources())
    }

    @InternalCoroutinesApi
    override fun dispatchYield(context: CoroutineContext, block: Runnable) {
        dispatcher.dispatchYield(context, block.withIdlingResources())
    }

    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        continuation.resumeWith(Result.success(Unit))
    }

    private fun Runnable.withIdlingResources() = Runnable {
        EspressoIdlingResource.increment()
        this.run()
        EspressoIdlingResource.decrement()
    }
}
