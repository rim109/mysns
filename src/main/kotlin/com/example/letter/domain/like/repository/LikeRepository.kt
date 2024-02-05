package com.example.letter.domain.like.repository

import com.example.letter.domain.like.dto.LikeResponse
import com.example.letter.domain.like.model.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun findAllByUserId(userId: Long): List<LikeResponse>

    fun findByLetterIdAndUserId(letterId: Long, userId: Long): Like?

    fun countLikeByLetterId(letterId: Long): Long
}