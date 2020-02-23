package com.tarek360.domain.interactor

import com.tarek360.domain.model.Image
import com.tarek360.domain.repo.ImagesRepository

class SearchResultInteractorImpl(
    private val imagesRepository: ImagesRepository
) : SearchResultInteractor {

    override fun searchImages(query: String, pageNumber: Int, pageSize: Int): List<Image> {
        return imagesRepository.searchImages(
            query = query,
            pageNumber = pageNumber,
            pageSize = pageSize
        )
    }
}
