package com.example.letter.domain.user.service

import com.example.letter.common.exception.InvalidInputException
import com.example.letter.common.exception.ModelNotFoundException
import com.example.letter.common.security.jwt.JwtPlugin
import com.example.letter.domain.user.dto.LoginRequest
import com.example.letter.domain.user.dto.LoginResponse
import com.example.letter.domain.user.dto.SignupRequest
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.model.User
import com.example.letter.domain.user.model.UserRole
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {
    override fun signup(request: SignupRequest): UserResponse {
        var email = userRepository.existsByEmail(request.email)

        val user = userRepository.save(
            User(
                nickname = request.nickname,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                birthDate = request.birthDate,
                phoneNumber = request.phoneNumber,
                info = request.info,
                role = when (request.role.toString()){
                    "USER" -> UserRole.USER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw InvalidInputException("dddd")

                }.toString()
            )
        )
        return UserResponse.from(user)
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        return LoginResponse(
            token = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role
            )
        )
    }
}