package com.example.letter.domain.user.service

import com.example.letter.domain.user.dto.LoginRequest
import com.example.letter.domain.user.dto.LoginResponse
import com.example.letter.domain.user.dto.SignupRequest
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.model.User
import com.example.letter.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun signup(request: SignupRequest): UserResponse {
        val user = userRepository.existsByEmail(request.email)
        userRepository.save(
            User(
                nickname = request.nickname,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                birthDate = request.birthDate,
                phoneNumber = request.phoneNumber,
                info = request.info,
                role = request.role.toString()
            )
        )
        return UserResponse.from(user!!)
    }

    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}