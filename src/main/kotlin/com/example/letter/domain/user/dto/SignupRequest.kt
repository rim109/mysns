package com.example.letter.domain.user.dto

import com.example.letter.domain.user.model.UserRole

data class SignupRequest(
    val nickname: String,
    val email: String,
    val password: String,
    val birthDate: String,
    val phoneNumber: String,
    val info: String,
    val role: UserRole
)
