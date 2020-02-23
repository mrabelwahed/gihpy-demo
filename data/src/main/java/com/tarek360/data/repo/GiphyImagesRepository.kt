package com.tarek360.data.repo

import com.tarek360.data.mapper.GiphyImageMapper
import com.tarek360.data.network.GiphyApi
import com.tarek360.domain.model.Image
import com.tarek360.domain.repo.ImagesRepository

class GiphyImagesRepository(
    private val giphyApi: GiphyApi,
    private val giphyImageMapper: GiphyImageMapper
) : ImagesRepository {

    override fun searchImages(query: String, pageNumber: Int, pageSize: Int): List<Image> {
        val response = giphyApi.searchImages(
            query = query,
            pageNumber = pageNumber,
            pageSize = pageSize
        ).execute().body()
        return giphyImageMapper.map(response?.data)
    }
}
