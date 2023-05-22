package com.commcode.vknewsclient.domain.repository

import com.commcode.vknewsclient.domain.entity.AuthState
import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun removePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}