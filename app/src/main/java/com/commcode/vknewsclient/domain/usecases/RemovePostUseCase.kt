package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class RemovePostUseCase @Inject constructor(
    private val repository: NewsFeedRepository,
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.removePost(feedPost)
    }
}