package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationsUseCase(
    private val repository: NewsFeedRepository,
) {

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}