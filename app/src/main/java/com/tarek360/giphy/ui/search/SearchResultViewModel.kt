package com.tarek360.giphy.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tarek360.domain.interactor.SearchResultInteractor
import com.tarek360.domain.model.Image
import com.tarek360.giphy.exhaustive
import com.tarek360.giphy.dispatcher.DispatcherProvider
import com.tarek360.giphy.ui.search.paging.GiphyDataSourceFactory
import com.tarek360.giphy.viewmodel.BaseViewModel
import javax.inject.Inject

open class SearchResultViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val searchResultInteractor: SearchResultInteractor,
    private val config: PagedList.Config
) : BaseViewModel<SearchResultAction, SearchResultState>(dispatcherProvider) {

    private val searchQuery = MutableLiveData<String>()
    private var loadingStateLiveData = MutableLiveData<SearchResultState.LoadingState>()
    private val errorStateLiveData = MutableLiveData<Throwable>()

    var livePagedList: LiveData<PagedList<Image>> = Transformations.switchMap(searchQuery) {

        val factory =
            GiphyDataSourceFactory(searchResultInteractor, it)

        LivePagedListBuilder<Int, Image>(factory, config)
            .setFetchExecutor {
                doJob(
                    job = { it.run() },
                    onError = { e -> errorStateLiveData.postValue(e) }
                )
            }
            .build()
    }

    override val viewState: MutableLiveData<SearchResultState> =
        MediatorLiveData<SearchResultState>().apply {
            addSource(livePagedList) { value = SearchResultState.DataState(SearchResultData(it)) }
            addSource(loadingStateLiveData) { value = SearchResultState.LoadingState }
            addSource(errorStateLiveData) { value = SearchResultState.ErrorState(it) }
        }

    override fun handleAction(action: SearchResultAction) {
        when (action) {
            is SearchResultAction.SearchAction -> search(action.query)
        }.exhaustive()
    }

    private fun search(query: String) {
        debounce(SEARCH_DEBOUNCE_TIME, job = {
            this.loadingStateLiveData.value = SearchResultState.LoadingState
            this.searchQuery.value = query
        })
    }

    companion object {
        private const val SEARCH_DEBOUNCE_TIME = 800L
    }
}
