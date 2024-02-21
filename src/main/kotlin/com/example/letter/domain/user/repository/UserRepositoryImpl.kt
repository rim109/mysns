package com.example.letter.domain.user.repository

import com.example.letter.common.queryDsl.QueryDslSupport
import com.example.letter.domain.user.model.User

class UserRepositoryImpl(
    private val userRepository: UserRepository
) : AUserRepository, QueryDslSupport() {
    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun existsByNickname(nickname: String): Boolean {
        return userRepository.existsByNickname(nickname)
    }

    override fun existsByPhoneNumber(phoneNumber: String): Boolean {
        return userRepository.existsByPhoneNumber(phoneNumber)
    }


}