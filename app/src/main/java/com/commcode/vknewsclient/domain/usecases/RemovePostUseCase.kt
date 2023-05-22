package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.repository.NewsFeedRepository

class RemovePostUseCase(
    private val repository: NewsFeedRepository,
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.removePost(feedPost)
    }
}