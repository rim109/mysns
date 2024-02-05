package com.example.letter.domain.user.dto

data class UserResponse(
    val id: Long,
    val nickname: String,
    val email: String,
    val birthDate: String,
    val phoneNumber: String,
    val info: String,
    val role: String,
    val createdAt: String,
    val updatedAt: String
)


