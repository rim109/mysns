package com.example.letter.domain.admin.service

import com.example.letter.domain.letter.dto.LetterPageResponse
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.user.dto.UserPageResponse
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate
import org.springframework.data.domain.Sort

interface AdminService {
    fun getLetterList(): List<LetterResponse>

    fun adminUpdateLetter(userId: Long, letterId: Long, request: LetterRequest): LetterResponse

    fun adminDeleteLetter(userId: Long, letterId: Long)

    fun getUserList(): List<UserResponse>

    fun getUserPage(pageNumber: Int, pageSize: Int, sort: String?, direction: Sort.Direction): UserPageResponse

    fun updateUser(userId: Long, request: UserUpdate): String

    fun getLetterPage(pageNumber: Int, pageSize: Int, sort: String?, direction: Sort.Direction): LetterPageResponse
}