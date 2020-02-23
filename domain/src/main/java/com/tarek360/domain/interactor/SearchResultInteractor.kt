package com.tarek360.domain.interactor

import com.tarek360.domain.model.Image

interface SearchResultInteractor {
    fun searchImages(query: String, pageNumber: Int, pageSize: Int): List<Image>
}
