package com.example.letter.domain.letter.service

import com.example.letter.domain.letter.dto.DeleteLetterRequest
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse

interface LetterService {

    fun getLetter(userId: Long, letterId: Long): LetterResponse

    fun createLetter(userId: Long, request: LetterRequest): LetterResponse

    fun deleteLetter(userId: Long, letterId: Long, request: DeleteLetterRequest)
}