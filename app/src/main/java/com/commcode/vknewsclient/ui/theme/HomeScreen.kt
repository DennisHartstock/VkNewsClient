package com.commcode.vknewsclient.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.commcode.vknewsclient.MainViewModel
import com.commcode.vknewsclient.domain.PostComment

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
) {
    val feedPosts = viewModel.feedPosts.observeAsState(listOf())

    if (feedPosts.value.isNotEmpty()) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(20) {
                add(PostComment(id = it))
            }
        }
        CommentsScreen(feedPost = feedPosts.value[0], comments = comments)
    }

//    LazyColumn(
//        modifier = Modifier.padding(paddingValues),
//        contentPadding = PaddingValues(
//            top = 16.dp,
//            bottom = 72.dp,
//            start = 8.dp,
//            end = 8.dp
//        ),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        items(
//            feedPosts.value,
//            { it.id }
//        ) { feedPost ->
//            val dismissState = rememberDismissState()
//
//            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
//                viewModel.remove(feedPost)
//            }
//
//            SwipeToDismiss(
//                modifier = Modifier.animateItemPlacement(),
//                state = dismissState,
//                background = {},
//                directions = setOf(DismissDirection.EndToStart)
//            ) {
//                PostCard(
//                    feedPost = feedPost,
//                    onViewsItemClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onSharesItemClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onCommentItemClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onLikesItemClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    }
//                )
//            }
//        }
//    }
}