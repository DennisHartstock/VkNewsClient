package com.commcode.vknewsclient.di

import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [CommentsViewModelModule::class]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance feedPost: FeedPost,
        ): CommentsScreenComponent
    }
}