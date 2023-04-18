package com.commcode.vknewsclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.commcode.vknewsclient.domain.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}