package com.commcode.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.commcode.vknewsclient.data.repository.NewsFeedRepository
import com.commcode.vknewsclient.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application,
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState = repository.getComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}