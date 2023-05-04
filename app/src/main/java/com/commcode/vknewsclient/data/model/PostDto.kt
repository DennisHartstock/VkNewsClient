package com.commcode.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id") val id: Long,
    @SerializedName("source_id") val groupId: Long,
    @SerializedName("is_favorite") val isFavorite: Boolean,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Long,
    @SerializedName("views") val views: ViewsDto,
    @SerializedName("reposts") val shares: SharesDto,
    @SerializedName("comments") val comments: CommentsDto,
    @SerializedName("likes") val likes: LikesDto,
    @SerializedName("attachments") val attachments: List<AttachmentDto>?,
)
