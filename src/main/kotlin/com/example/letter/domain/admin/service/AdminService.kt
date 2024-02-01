package com.example.letter.domain.admin.service

import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate

interface AdminService {
    fun getLetterList(): List<LetterResponse>

    fun adminUpdateLetter(letterId: Long, request: LetterRequest): LetterResponse

    fun adminDeleteLetter(letterId: Long)

    fun getUserList(): List<UserResponse>

    fun updateUser(userId: Long, request: UserUpdate): String
}