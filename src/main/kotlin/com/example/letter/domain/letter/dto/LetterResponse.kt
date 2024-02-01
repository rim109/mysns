package com.example.letter.domain.letter.dto

data class LetterResponse(
    val id: Long,
    val nickname: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String
)
