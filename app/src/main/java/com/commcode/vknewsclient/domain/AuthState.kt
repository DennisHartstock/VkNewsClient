package com.commcode.vknewsclient.domain

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()
}
