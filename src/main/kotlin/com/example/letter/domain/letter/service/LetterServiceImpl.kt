package com.example.letter.domain.letter.service

import com.example.letter.common.exception.InvalidPasswordException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.domain.letter.dto.DeleteLetterRequest
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.model.Letter
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.letter.repository.LetterRepository
import com.example.letter.domain.like.repository.LikeRepository
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterServiceImpl(
    private val letterRepository: LetterRepository,
    private val userRepository: UserRepository,
    private val likeRepository: LikeRepository
) : LetterService {
    override fun getLetter(letterId: Long): LetterResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter",letterId)

        val numLiked = likeRepository.countLikeByLetterId(letterId)
        letter.numLike = numLiked

        return letter.toResponse()
    }


    @Transactional
    override fun createLetter(userId: Long, request: LetterRequest): LetterResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User",userId)
        val createLetter = letterRepository.save(
            Letter(
                nickname = request.nickname!!,
                content = request.content,
                user = user,
                numLike = 0
            )

        )
        return createLetter.toResponse()
    }
    @Transactional
    override fun deleteLetter(userId: Long, letterId: Long, request: DeleteLetterRequest) {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter",letterId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        if (user.password != request.password)
            throw InvalidPasswordException(request.password)
        else {
            user.removeLetter(letter)
            letterRepository.save(letter)
        }
    }
}