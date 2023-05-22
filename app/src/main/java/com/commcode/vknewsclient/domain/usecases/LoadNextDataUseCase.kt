package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository,
) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}