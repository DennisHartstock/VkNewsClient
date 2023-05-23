package com.commcode.vknewsclient.presentation

import android.app.Application
import com.commcode.vknewsclient.di.ApplicationComponent
import com.commcode.vknewsclient.di.DaggerApplicationComponent
import com.commcode.vknewsclient.domain.entity.FeedPost

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this,
            FeedPost(0, 0, "", "", "", "", "", listOf(), false)
        )
    }
}