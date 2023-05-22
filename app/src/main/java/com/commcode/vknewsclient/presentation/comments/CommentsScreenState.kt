package com.commcode.vknewsclient.presentation.comments

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) : CommentsScreenState()
}