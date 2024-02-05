package com.example.letter.domain.like.service

import com.example.letter.domain.like.dto.LikeResponse

interface LikeService {
    fun likeLetter(letterId: Long, userId: Long): LikeResponse

    fun likeChecking(userId: Long): List<LikeResponse>
}