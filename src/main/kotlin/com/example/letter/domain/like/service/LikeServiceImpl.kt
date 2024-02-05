package com.example.letter.domain.like.service

import com.example.letter.common.exception.LikeException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.domain.letter.repository.LetterRepository
import com.example.letter.domain.like.dto.LikeResponse
import com.example.letter.domain.like.model.Like
import com.example.letter.domain.like.model.toResponse
import com.example.letter.domain.like.repository.LikeRepository
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository,
    private val likeRepository: LikeRepository
) : LikeService {
    override fun likeLetter(letterId: Long, userId: Long): LikeResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter", letterId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        val liking = likeRepository.findByLetterIdAndUserId(letterId, userId)

        return if (liking == null) {
            val likes = likeRepository.save(Like(letter = letter, user = user, liked = true))
            if (letter.user.id != userId) {
                letter.numLike++
                letterRepository.save(letter)
                likes.toResponse()
            } else {
                throw LikeException(letterId, userId)
            }
        } else {
            likeRepository.delete(liking)
            letter.numLike--
            letterRepository.save(letter)
            liking.toResponse().copy(liked = false)
        }

    }

    override fun likeChecking(userId: Long): List<LikeResponse> {
        return likeRepository.findAllByUserId(userId)
    }
}