package com.commcode.vknewsclient.ui.theme

import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) : CommentsScreenState()
}