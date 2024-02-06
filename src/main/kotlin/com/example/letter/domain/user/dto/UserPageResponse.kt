package com.example.letter.domain.user.dto


data class UserPageResponse (
    val content: List<UserResponse>,
    val totalPages: Int,
    val totalLetter: Long
)