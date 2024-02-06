package com.example.letter.domain.letter.dto

data class LetterPageResponse(
    val content: List<LetterResponse>,
    val totalPages: Int,
    val totalLetter: Long
)
