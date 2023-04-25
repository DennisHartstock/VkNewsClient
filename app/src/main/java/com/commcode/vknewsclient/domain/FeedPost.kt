package com.commcode.vknewsclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.commcode.vknewsclient.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
    val avatarResId: Int = R.drawable.post_community_thumbnail,
    val communityName: String = "Community title",
    val publicationDate: String = "00:00",
    val postContent: String = "Post content",
    val postImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 0),
        StatisticItem(StatisticType.SHARES, 0),
        StatisticItem(StatisticType.COMMENTS, 0),
        StatisticItem(StatisticType.LIKES, 0)
    ),
) : Parcelable {

    companion object {

        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {

            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
