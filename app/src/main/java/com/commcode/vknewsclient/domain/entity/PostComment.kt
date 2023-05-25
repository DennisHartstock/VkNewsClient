package com.commcode.vknewsclient.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class PostComment(
    val id: Long,
    val authorAvatarUrl: String,
    val authorName: String,
    val commentText: String,
    val publicationDate: String,
)
