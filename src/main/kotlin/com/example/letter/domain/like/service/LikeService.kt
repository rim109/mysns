package com.example.letter.domain.like.service

import com.example.letter.domain.like.dto.LikePageResponse
import com.example.letter.domain.like.dto.LikeResponse
import org.springframework.data.domain.Sort

interface LikeService {
    fun likeLetter(letterId: Long, userId: Long): LikeResponse

    fun likeChecking(userId: Long, pageNumber: Int, pageSize: Int, sort: String?, direction: Sort.Direction): LikePageResponse
}