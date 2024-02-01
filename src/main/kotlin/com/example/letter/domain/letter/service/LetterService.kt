package com.example.letter.domain.letter.service

import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse

interface LetterService {

    fun getLetter(letterId: Long): LetterResponse

    fun createLetter(userId: Long, request: LetterRequest): LetterResponse

    fun deleteLetter(letterId: Long)
}