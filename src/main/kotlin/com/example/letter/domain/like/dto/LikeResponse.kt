package com.example.letter.domain.like.dto

data class LikeResponse(
    val userId: Long,
    val letterId: Long,
    val liked: Boolean
)
