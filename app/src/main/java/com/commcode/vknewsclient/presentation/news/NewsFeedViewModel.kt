package com.commcode.vknewsclient.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.commcode.vknewsclient.data.repository.NewsFeedRepository
import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(application)

    private val recommendationsFlow = repository.recommendations

    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    val screenState = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationsFlow.value,
                    nextDataIsLoading = true
                )
            )
            repository.loadNextData()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.removePost(feedPost)
        }
    }
}