package com.commcode.vknewsclient.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.commcode.vknewsclient.R
import com.commcode.vknewsclient.domain.FeedPost
import com.commcode.vknewsclient.domain.StatisticItem
import com.commcode.vknewsclient.domain.StatisticType
import com.commcode.vknewsclient.ui.theme.LightRed

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onSharesItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit,
    onLikesItemClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = feedPost.postContent)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = feedPost.contentImageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                feedPost.statistics,
                onViewsItemClickListener = onViewsItemClickListener,
                onSharesItemClickListener = onSharesItemClickListener,
                onCommentItemClickListener = onCommentItemClickListener,
                onLikesItemClickListener = onLikesItemClickListener,
                isFavorite = feedPost.isLiked
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onSharesItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit,
    onLikesItemClickListener: (StatisticItem) -> Unit,
    isFavorite: Boolean,
) {
    Row {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = formatStatisticCount(viewsItem.count),
                onItemClickListener = {
                    onViewsItemClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(2f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = formatStatisticCount(sharesItem.count),
                onItemClickListener = {
                    onSharesItemClickListener(sharesItem)
                }
            )
            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = formatStatisticCount(commentItem.count),
                onItemClickListener = {
                    onCommentItemClickListener(commentItem)
                }
            )
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = if (isFavorite) R.drawable.ic_like_set else R.drawable.ic_like,
                text = formatStatisticCount(likesItem.count),
                onItemClickListener = {
                    onLikesItemClickListener(likesItem)
                },
                tint = if (isFavorite) LightRed else MaterialTheme.colors.onSecondary
            )
        }
    }
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", count / 1000)
    } else if (count > 1_000) {
        String.format("%.1fK", count / 1_000f)
    } else {
        count.toString()
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit,
    tint: Color = MaterialTheme.colors.onSecondary,
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = MaterialTheme.colors.onSecondary)
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.groupImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = feedPost.groupName,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "Menu",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}