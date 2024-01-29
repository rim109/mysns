package com.example.letter.domain.letter.service

import com.example.letter.domain.letter.dto.LetterResponse

interface LetterService {

    fun getLetter(letterId: Long): LetterResponse

    fun deleteLetter(letterId: Long)
}