package com.tarek360.giphy.ui.search.paging

import androidx.paging.PageKeyedDataSource
import com.tarek360.domain.interactor.SearchResultInteractor
import com.tarek360.domain.model.Image

class GiphyDataSource(
    private val searchResultInteractor: SearchResultInteractor,
    private val searchQuery: String
) : PageKeyedDataSource<Int, Image>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Image>
    ) {
        val nextPage = FIRST_PAGE_OFFSET + 1
        val result = searchResultInteractor.searchImages(
            searchQuery,
            FIRST_PAGE_OFFSET,
            params.requestedLoadSize
        )
        callback.onResult(result, null, nextPage)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        val currentPage = params.key
        val result =
            searchResultInteractor.searchImages(searchQuery, currentPage, params.requestedLoadSize)

        val nextPage = currentPage + 1
        callback.onResult(result, nextPage)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        // No need to prepend.
    }

    companion object {
        private const val FIRST_PAGE_OFFSET = 0
    }
}

