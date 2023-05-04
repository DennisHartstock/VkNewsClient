package com.commcode.vknewsclient.data.mapper

import com.commcode.vknewsclient.data.model.NewsFeedResponseDto
import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.domain.StatisticItem
import com.commcode.vknewsclient.domain.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val groups = responseDto.newsFeedContent.groups
        val posts = responseDto.newsFeedContent.posts

        for (post in posts) {
            val group = groups.find { it.id == post.groupId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                groupId = post.groupId,
                groupImageUrl = group.imageUrl,
                groupName = group.name,
                publicationDate = mapTimestampToDate(post.date * 1000),
                postContent = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, count = post.views.count),
                    StatisticItem(type = StatisticType.SHARES, count = post.shares.count),
                    StatisticItem(type = StatisticType.COMMENTS, count = post.comments.count),
                    StatisticItem(type = StatisticType.LIKES, count = post.likes.count)
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("dd MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}