package com.tarek360.giphy.concurrency

import com.tarek360.giphy.dispatcher.DispatcherProvider
import kotlinx.coroutines.*

class CoroutineJobHandler(
    private val coroutineScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider
) {
    private var job: Job? = null

    fun debounce(timeMillis: Long = 0, block: () -> Unit) {
        job?.cancel()
        job = work(
            dispatcher = dispatcherProvider.main(),
            block = {
                delay(timeMillis)
                block()
            }
        )
    }

    fun doJob(block: suspend () -> Unit, onError: (Throwable) -> Unit) {
        work(dispatcherProvider.io(), block, onError)
    }

    private fun work(
        dispatcher: CoroutineDispatcher,
        block: suspend () -> Unit,
        onError: ((Throwable) -> Unit)? = null
    ): Job {
        return coroutineScope.launch(dispatcher) {
            try {
                block()
            } catch (e: Exception) {
                onError?.invoke(e)
            }
        }
    }


//    val doJob: (suspend () -> Unit) -> Unit = {
//        coroutineScope.launch(dispatcher) {
//            it()
//        }
//    }

//    suspend fun <T> doJob(context: CoroutineContext = dispatcher, block: () -> T): JobResult<T> =
//        withContext(context) {
//            try {
//                JobResult.Success(block())
//            } catch (e: Exception) {
//                JobResult.Failure<T>(e)
//            }
//        }
//
//    suspend fun <T> doJob(
//        timeMillis: Long,
//        context: CoroutineContext = dispatcher,
//        block: () -> T
//    ): JobResult<T> {
//        job?.cancel()
//        delay(timeMillis)
//        return doJob(context, block)
//    }


}