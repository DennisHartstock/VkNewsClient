package com.commcode.vknewsclient.navigation

import com.commcode.vknewsclient.domain.FeedPost

sealed class Screen(
    val route: String,
) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)

    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${feedPost.id}/${feedPost.postContent}"
        }
    }

    companion object {

        const val KEY_FEED_POST_ID = "feed_post_id"
        const val KEY_POST_CONTENT = "content_text"

        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_POST_CONTENT}"
    }
}