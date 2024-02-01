package com.example.letter.domain.letter.dto

import jakarta.validation.constraints.Max

data class LetterRequest(
    @Max(500)
    val nickname: String?,
    @Max(5000)
    val content: String
)
