package com.commcode.vknewsclient.presentation

import android.app.Application
import com.commcode.vknewsclient.di.ApplicationComponent
import com.commcode.vknewsclient.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}