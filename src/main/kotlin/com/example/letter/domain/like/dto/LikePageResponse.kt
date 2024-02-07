package com.example.letter.domain.like.dto


data class LikePageResponse(
    val content: List<LikeResponse>,
    val totalPages: Int,
    val totalLikes: Long
)