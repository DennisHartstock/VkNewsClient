package com.commcode.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.commcode.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application,
) : ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)
    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}