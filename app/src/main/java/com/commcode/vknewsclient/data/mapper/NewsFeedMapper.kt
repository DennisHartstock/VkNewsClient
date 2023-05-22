package com.commcode.vknewsclient.data.mapper

import com.commcode.vknewsclient.data.model.CommentsResponseDto
import com.commcode.vknewsclient.data.model.NewsFeedResponseDto
import com.commcode.vknewsclient.domain.entity.FeedPost
import com.commcode.vknewsclient.domain.entity.PostComment
import com.commcode.vknewsclient.domain.entity.StatisticItem
import com.commcode.vknewsclient.domain.entity.StatisticType
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
                publicationDate = mapTimestampToDate(post.date),
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

    fun mapResponseToComments(response: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val comments = response.content.comments
        val profiles = response.content.profiles
        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("dd MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}