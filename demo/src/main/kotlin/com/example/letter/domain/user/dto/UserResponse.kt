package com.example.letter.domain.user.dto

import com.example.letter.domain.user.model.User
import com.example.letter.domain.user.model.UserRole

data class UserResponse(
    val id: Long,
    val nickname: String,
    val email: String,
    val password: String,
    val birthDate: String,
    val phoneNumber: String,
    val info: String,
    val role: String
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                user.id!!,
                user.nickname,
                user.email,
                user.password,
                user.birthDate,
                user.phoneNumber,
                user.info,
                user.role
            )
        }
    }
}


