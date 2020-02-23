package com.tarek360.giphy.ui.search.paging

import androidx.paging.DataSource
import com.tarek360.domain.interactor.SearchResultInteractor
import com.tarek360.domain.model.Image

class GiphyDataSourceFactory(
    private val searchResultInteractor: SearchResultInteractor,
    private val searchQuery: String
) : DataSource.Factory<Int, Image>() {

    override fun create(): DataSource<Int, Image> {
        return GiphyDataSource(
            searchResultInteractor,
            searchQuery
        )
    }
}
