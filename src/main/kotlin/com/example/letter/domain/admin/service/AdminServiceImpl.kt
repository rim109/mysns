package com.example.letter.domain.admin.service

import com.example.letter.common.exception.HttpMessageNotReadableException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.letter.repository.LetterRepository
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate
import com.example.letter.domain.user.model.UserRole
import com.example.letter.domain.user.model.toResponse
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminServiceImpl(
    private val letterRepository: LetterRepository,
    private val userRepository: UserRepository,
) : AdminService {
    override fun getLetterList(): List<LetterResponse> {
        return letterRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).map { it.toResponse() }
    }

    @Transactional
    override fun adminUpdateLetter(userId: Long, letterId: Long, request: LetterRequest): LetterResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter", letterId)
        letter.nickname = request.nickname ?: letter.nickname
        letter.content = request.content

        return letter.toResponse()
    }

    @Transactional
    override fun adminDeleteLetter(userId: Long, letterId: Long) {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter", letterId)
        letterRepository.delete(letter)
        letterRepository.save(letter)
    }

    override fun getUserList(): List<UserResponse> {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).map { it.toResponse() }
    }


    @Transactional
    override fun updateUser(userId: Long, request: UserUpdate): String {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        if (request.role != UserRole.ADMIN && request.role != UserRole.USER) {
            throw HttpMessageNotReadableException("Choose ADMIN OR USER")
        } else {
            user.role = request.role

            userRepository.save(user)
        }


        return "등급이 변경되었습니다."
    }
}