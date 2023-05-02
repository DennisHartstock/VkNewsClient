package com.commcode.vknewsclient.data.network

import com.commcode.vknewsclient.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun getRecommended(
        @Query("access_token") token: String,
    ): NewsFeedResponseDto
}