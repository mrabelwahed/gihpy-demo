package com.tarek360.giphy

import com.tarek360.giphy.dispatcher.DispatcherProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@InternalCoroutinesApi
class CoroutineDispatcherIgnoreDelay(private val dispatcher: CoroutineDispatcher) :
    CoroutineDispatcher(), Delay {

    @ExperimentalCoroutinesApi
    override fun isDispatchNeeded(context: CoroutineContext): Boolean =
        dispatcher.isDispatchNeeded(context)

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatcher.dispatch(context, block)
    }

    @InternalCoroutinesApi
    override fun dispatchYield(context: CoroutineContext, block: Runnable) {
        dispatcher.dispatchYield(context, block)
    }

    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        continuation.resumeWith(Result.success(Unit))
    }
}

@InternalCoroutinesApi
val coroutineDispatcherIgnoredDelay = object : DispatcherProvider {
    override fun io(): CoroutineDispatcher = CoroutineDispatcherIgnoreDelay(super.io())

    override fun main(): CoroutineDispatcher = CoroutineDispatcherIgnoreDelay(super.main())
}