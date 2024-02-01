package com.example.letter.domain.user.service

import com.example.letter.domain.user.dto.LoginRequest
import com.example.letter.domain.user.dto.LoginResponse
import com.example.letter.domain.user.dto.SignupRequest
import com.example.letter.domain.user.dto.UserResponse

interface UserService {

    fun signup(request: SignupRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse

    fun getUser(userId: Long): UserResponse

}