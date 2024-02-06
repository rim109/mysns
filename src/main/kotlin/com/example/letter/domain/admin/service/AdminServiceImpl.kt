package com.example.letter.domain.admin.service

import com.example.letter.common.exception.HttpMessageNotReadableException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.domain.letter.dto.LetterPageResponse
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.model.Letter
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.letter.repository.LetterRepository
import com.example.letter.domain.like.repository.LikeRepository
import com.example.letter.domain.user.dto.UserPageResponse
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate
import com.example.letter.domain.user.model.User
import com.example.letter.domain.user.model.UserRole
import com.example.letter.domain.user.model.toResponse
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminServiceImpl(
    private val letterRepository: LetterRepository,
    private val userRepository: UserRepository,
    private val likeRepository: LikeRepository
) : AdminService {
    override fun getLetterList(): List<LetterResponse> {
        return letterRepository.findAll().map { it.toResponse() }
    }

    override fun getLetterPage(
        pageNumber: Int,
        pageSize: Int,
        sort: String?,
        direction: Sort.Direction
    ): LetterPageResponse {
        val pageable: PageRequest = PageRequest.of(pageNumber - 1, pageSize, direction, sort)
        val lettersPage: Page<Letter> = letterRepository.findAll(pageable)
        val letterResponseList: List<LetterResponse> = lettersPage.content.map { it.toResponse() }

        return LetterPageResponse(
            content = letterResponseList,
            totalPages = lettersPage.totalPages,
            totalLetter = lettersPage.totalElements
        )
    }


    @Transactional
    override fun adminUpdateLetter(userId: Long, letterId: Long, request: LetterRequest): LetterResponse {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter", letterId)
        letter.nickname = request.nickname ?: letter.nickname
        letter.content = request.content

        val numLiked = likeRepository.countLikeByLetterId(letterId)
        letter.numLike = numLiked

        return letter.toResponse()
    }

    @Transactional
    override fun adminDeleteLetter(userId: Long, letterId: Long) {
        val letter = letterRepository.findByIdOrNull(letterId) ?: throw ModelNotFoundException("Letter", letterId)
        letterRepository.delete(letter)
        letterRepository.save(letter)
    }

//    override fun getUserList(): List<UserResponse> {
//        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).map { it.toResponse() }
//    }

    override fun getUserList(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }


    override fun getUserPage(
        pageNumber: Int,
        pageSize: Int,
        sort: String?,
        direction: Sort.Direction
    ): UserPageResponse {
        val pageable: PageRequest = PageRequest.of(pageNumber - 1, pageSize, direction, sort)
        val usersPage: Page<User> = userRepository.findAll(pageable)
        val usersResponseList: List<UserResponse> = usersPage.content.map { it.toResponse() }

        return UserPageResponse(
            content = usersResponseList,
            totalPages = usersPage.totalPages,
            totalLetter = usersPage.totalElements
        )
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