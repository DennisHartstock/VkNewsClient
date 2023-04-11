package com.commcode.vknewsclient.domain

import com.commcode.vknewsclient.R

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
)
