package com.example.letter.domain.user.repository

import com.example.letter.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByNickname(nickname: String): User?

//    fun findByDeleted(iSDeleted: Boolean): Boolean
//
    fun deleteUserByDeletedAndCreatedAtLessThanEqual(deleted: Boolean, createdAt: String)

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun existsByPhoneNumber(phoneNumber: String): Boolean
}