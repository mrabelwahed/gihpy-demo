package com.tarek360.giphy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarek360.giphy.concurrency.CoroutineJobHandler
import com.tarek360.giphy.dispatcher.DispatcherProvider

abstract class BaseViewModel<Action, State>(
    dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val coroutineJobHandler = CoroutineJobHandler(viewModelScope, dispatcherProvider)

//    protected val doJob: (suspend () -> Unit) -> Unit = { coroutineJobHandler.doJob { it() } }
//    protected fun debounce(timeMillis: Long) : (suspend () -> Unit) -> Unit = { coroutineJobHandler.debounce(timeMillis) { it() } }

    protected fun debounce(
        timeMillis: Long,
        job: () -> Unit
    ) {
        coroutineJobHandler.debounce(timeMillis, job)
    }

    protected fun doJob(
        job: suspend () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        coroutineJobHandler.doJob(job, onError)
    }

//    private val viewStateLiveData = MutableLiveData<State>()
//
//    val viewState: LiveData<State>
//        get() = viewStateLiveData

    abstract val viewState: MutableLiveData<State>

//    protected fun setState(state: State) {
//        viewStateLiveData.value = state
//    }

    abstract fun handleAction(action: Action)
}
