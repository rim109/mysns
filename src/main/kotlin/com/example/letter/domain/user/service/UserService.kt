package com.example.letter.domain.user.service

import com.example.letter.domain.user.dto.*

interface UserService {

    fun signup(request: SignupRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse

    fun getUser(userId: Long): UserResponse

    fun updateUser(userId: Long, request: UpdateUserRequest): UserResponse

    fun deleteUser(userId: Long)

}