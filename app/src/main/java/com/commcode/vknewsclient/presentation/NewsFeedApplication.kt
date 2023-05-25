package com.commcode.vknewsclient.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.commcode.vknewsclient.di.ApplicationComponent
import com.commcode.vknewsclient.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}