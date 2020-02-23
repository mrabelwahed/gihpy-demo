package com.tarek360.data.network

import com.tarek360.data.BuildConfig
import com.tarek360.data.model.GiphySearchNetworkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("v1/gifs/search")
    fun searchImages(
        @Query("q") query: String,
        @Query("offset") pageNumber: Int = 1,
        @Query("limit") pageSize: Int = 20,
        @Query("api_key") apiKey: String = BuildConfig.GIPHY_API_KEY
    ): Call<GiphySearchNetworkResponse?>
}

