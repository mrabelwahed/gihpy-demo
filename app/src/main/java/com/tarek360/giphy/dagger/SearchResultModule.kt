package com.tarek360.giphy.dagger

import androidx.paging.PagedList
import com.tarek360.data.mapper.GiphyImageMapper
import com.tarek360.data.network.GiphyApi
import com.tarek360.data.repo.GiphyImagesRepository
import com.tarek360.domain.interactor.SearchResultInteractor
import com.tarek360.domain.interactor.SearchResultInteractorImpl
import com.tarek360.domain.repo.ImagesRepository
import dagger.Module
import dagger.Provides

@Module
class SearchResultModule {

    @Provides
    fun provideGiphyImagesRepository(
        giphyApi: GiphyApi
    ): ImagesRepository = GiphyImagesRepository(
        giphyApi,
        GiphyImageMapper()
    )

    @Provides
    fun provideSearchResultInteractor(
        imagesRepository: ImagesRepository
    ): SearchResultInteractor = SearchResultInteractorImpl(imagesRepository)

    @Provides
    fun provideLivePagedList(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
