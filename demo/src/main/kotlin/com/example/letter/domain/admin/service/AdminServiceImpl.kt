package com.example.letter.domain.admin.service

import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.letter.repository.LetterRepository
import com.example.letter.domain.letter.service.LetterService
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl(
    private val letterRepository: LetterRepository
): AdminService {
    override fun getLetterList(): List<LetterResponse> {
        return letterRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt")).map { it.toResponse() }
    }

    override fun adminUpdateLetter(letterId: Long, request: LetterRequest): LetterResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw IllegalArgumentException("DDD")
        letter.nickname = request.nickname
        letter.content = request.content

        return letter.toResponse()
    }

    override fun adminDeleteLetter(letterId: Long) {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw IllegalArgumentException("DDD")
        letterRepository.delete(letter)
        letterRepository.save(letter)
    }

}