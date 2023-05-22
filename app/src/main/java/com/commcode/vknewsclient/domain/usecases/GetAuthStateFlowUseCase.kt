package com.commcode.vknewsclient.domain.usecases

import com.commcode.vknewsclient.domain.entity.AuthState
import com.commcode.vknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateFlowUseCase(
    private val repository: NewsFeedRepository,
) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}