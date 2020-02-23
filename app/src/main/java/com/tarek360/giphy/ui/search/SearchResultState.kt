package com.tarek360.giphy.ui.search

import androidx.paging.PagedList
import com.tarek360.domain.model.Image

sealed class SearchResultState {
    object LoadingState : SearchResultState()
    data class DataState(val data: SearchResultData) : SearchResultState()
    data class ErrorState(val t: Throwable) : SearchResultState()
}

data class SearchResultData(
    val images: PagedList<Image>
)
