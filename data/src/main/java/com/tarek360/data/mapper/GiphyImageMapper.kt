package com.tarek360.data.mapper

import com.tarek360.data.model.GiphyImageData
import com.tarek360.domain.model.Image

class GiphyImageMapper {

    fun map(input: List<GiphyImageData>?): List<Image> =
        input?.mapNotNull { map(it) } ?: emptyList()

    private fun map(input: GiphyImageData): Image? {
        val normalFramesUrl = input.sizes?.normalFrames?.url
        val lowFramesUrl = input.sizes?.lowFrames?.url

        return if (normalFramesUrl != null && lowFramesUrl != null) {
            Image(
                lowFramesUrl = lowFramesUrl,
                normalFramesUrl = normalFramesUrl
            )
        } else {
            null
        }
    }
}



