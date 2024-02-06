package com.example.letter.domain.user.dto

import com.example.letter.domain.letter.dto.LetterResponse

data class UserResponse(
    val id: Long,
    val nickname: String,
    val email: String,
    val birthDate: String,
    val phoneNumber: String,
    val info: String,
    val role: String,
    val createdAt: String,
    val updatedAt: String,
    var letter: List<LetterResponse>
)


