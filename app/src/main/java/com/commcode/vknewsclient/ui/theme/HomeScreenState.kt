package com.commcode.vknewsclient.ui.theme

import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.domain.PostComment

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()

    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}