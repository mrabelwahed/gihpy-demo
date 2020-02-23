package com.tarek360.domain.repo

import com.tarek360.domain.model.Image

interface ImagesRepository {
    fun searchImages(query: String, pageNumber: Int, pageSize: Int): List<Image>
}
