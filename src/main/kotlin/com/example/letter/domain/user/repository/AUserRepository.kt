package com.example.letter.domain.user.repository

import com.example.letter.domain.user.model.User

interface AUserRepository {

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean
}