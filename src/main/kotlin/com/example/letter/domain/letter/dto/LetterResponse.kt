package com.example.letter.domain.letter.dto

data class LetterResponse(
    val id: Long,
    val userId: Long,
    val nickname: String,
    val content: String,
    val numLike: Int,
    val createdAt: String,
    val updatedAt: String
)
