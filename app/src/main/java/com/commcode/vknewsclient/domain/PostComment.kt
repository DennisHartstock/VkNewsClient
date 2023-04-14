package com.commcode.vknewsclient.domain

import com.commcode.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorAvatarId: Int = R.drawable.post_community_thumbnail,
    val authorName: String = "Author name",
    val commentText: String = "Comment text",
    val publicationDate: String = "00:00",
)
