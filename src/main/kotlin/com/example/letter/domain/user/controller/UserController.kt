package com.example.letter.domain.user.controller

import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.user.dto.LoginRequest
import com.example.letter.domain.user.dto.LoginResponse
import com.example.letter.domain.user.dto.SignupRequest
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request))
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(
        @Valid @RequestBody request: SignupRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(request))
    }

    @Operation(summary = "user 단건 조회")
    @PreAuthorize("hasRole('ADMIN') or #userPrincipal.id == #userId")
    @GetMapping("/users/{userId}")
    fun getUser(
        @PathVariable userId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId))
    }
}