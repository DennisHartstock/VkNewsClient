package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.entity.PostComment
import com.commcode.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(
    private val repository: NewsFeedRepository,
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}