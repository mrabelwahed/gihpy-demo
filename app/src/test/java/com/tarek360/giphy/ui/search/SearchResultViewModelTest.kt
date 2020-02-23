package com.tarek360.giphy.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tarek360.domain.interactor.SearchResultInteractor
import com.tarek360.domain.model.Image
import com.tarek360.giphy.coroutineDispatcherIgnoredDelay
import com.tarek360.giphy.livedatatesting.test
import com.tarek360.test.CoroutinesTestRule
import com.tarek360.test.isInstanceOf
import com.tarek360.test.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class SearchResultViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val dispatcherProvider = coroutineDispatcherIgnoredDelay
    private var searchResultInteractor: SearchResultInteractor = mock()
    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    private val liveDataPagedList = MutableLiveData<PagedList<Image>>()

    private val viewModel: SearchResultViewModel by lazy {
        SearchResultViewModel(dispatcherProvider, searchResultInteractor, config).apply {
            livePagedList = liveDataPagedList
        }
    }

    @Test
    fun testHandleSearchAction() {
        // Arrange
        whenever(searchResultInteractor.searchImages(any(), any(), any())).thenReturn(emptyList())

        coroutinesTestRule.testDispatcher.runBlockingTest {
            val testObserver = viewModel.viewState.test()

            // Act
            viewModel.handleAction(SearchResultAction.SearchAction("key"))
            liveDataPagedList.value = mock()

            // Assert
            testObserver.assertHistorySize(2)
            testObserver.firstValue() shouldEqual SearchResultState.LoadingState
            testObserver.secondValue() isInstanceOf SearchResultState.DataState::class

            // Clean
            viewModel.viewState.removeObserver(testObserver)
        }
    }
}
