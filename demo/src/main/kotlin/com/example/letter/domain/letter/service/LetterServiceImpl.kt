package com.example.letter.domain.letter.service

import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.letter.repository.LetterRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LetterServiceImpl(
    private val letterRepository: LetterRepository
) : LetterService {
    override fun getLetter(letterId: Long): LetterResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw IllegalArgumentException("DDD")
        return letter.toResponse()
    }

    override fun deleteLetter(letterId: Long) {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw IllegalArgumentException("DDD")
        return letterRepository.delete(letter)
    }
}