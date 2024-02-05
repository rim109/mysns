package com.example.letter.domain.user.repository

import com.example.letter.domain.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

//    fun findByUser(pageable: Pageable): Page<User>

}