package com.example.letter.domain.admin.service

import com.example.letter.domain.letter.dto.LetterResponse

interface AdminService {
    fun getLetterList(): List<LetterResponse>

    fun adminUpdateLetter(letterId: Long): LetterResponse

    fun adminDeleteLetter(letterId: Long)
}