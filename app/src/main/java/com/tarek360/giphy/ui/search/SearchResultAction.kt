package com.tarek360.giphy.ui.search

sealed class SearchResultAction {
    data class SearchAction(val query: String) : SearchResultAction()
}
