package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository,
) {

    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}