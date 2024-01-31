package com.example.letter.domain.admin.service

import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse

interface AdminService {
    fun getLetterList(): List<LetterResponse>

    fun adminUpdateLetter(letterId: Long, request: LetterRequest): LetterResponse

    fun adminDeleteLetter(letterId: Long)
}