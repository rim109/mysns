package com.example.letter.domain.user.service

import com.example.letter.common.exception.InvalidRoleException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.common.security.jwt.JwtPlugin
import com.example.letter.domain.user.dto.LoginRequest
import com.example.letter.domain.user.dto.LoginResponse
import com.example.letter.domain.user.dto.SignupRequest
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.model.*
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {
    override fun signup(request: SignupRequest): UserResponse {
        checkingEmailAndNicknameAndPhoneNumberExists(request.email, request.nickname, request.phoneNumber,userRepository)
        passwordNoHaveNickname(request.nickname,request.password)
        passwordMisMatch(request.password, request.passwordConfirm)

        val users = userRepository.save(
            User(
                nickname = request.nickname,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                birthDate = request.birthDate,
                phoneNumber = request.phoneNumber,
                info = request.info,
                role = when (request.role){
                    "USER" -> UserRole.USER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw InvalidRoleException(request.role)
                }
            )
        )
        return users.toResponse()
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        return LoginResponse(
            token = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.toString()
            )
        )
    }

    override fun getUser(userId: Long): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        return user.toResponse()
    }

    override fun deleteUser(userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.deleteUser()
        userRepository.save(user)
    }
}