package com.tarek360.data.model

import com.google.gson.annotations.SerializedName

data class GiphySearchNetworkResponse(
    @SerializedName("data") val data: List<GiphyImageData>
)

data class GiphyImageData(
    @SerializedName("images") val sizes: GiphySizes?
)

data class GiphySizes(
    @SerializedName("fixed_width") val normalFrames: GiphyImage?,
    @SerializedName("fixed_width_downsampled") val lowFrames: GiphyImage?
)

data class GiphyImage(
    @SerializedName("url") val url: String?
)
