package com.example.letter.domain.admin.controller

import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.admin.service.AdminService
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/admins")
@RestController
class AdminController(
    private val adminService: AdminService
) {

    @Operation(summary = "letter 전체 조회 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/letter")
    fun getListLetter(): ResponseEntity<List<LetterResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.getLetterList())
    }

    @Operation(summary = "letter 수정 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/letter/{letterId}")
    fun adminUpdateLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long,
        @RequestBody request: LetterRequest
    ): ResponseEntity<LetterResponse> {
        val userId = userPrincipal.id
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.adminUpdateLetter(userId, letterId, request))
    }

    @Operation(summary = "letter 삭제 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/letter/{letterId}")
    fun adminDeleteLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        adminService.adminDeleteLetter(letterId,userId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @Operation(summary = "user 전체 조회 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{userId}")
    fun getUserList(): ResponseEntity<List<UserResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserList())
    }

    @Operation(summary = "user 등급 승격 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{userId}")
    fun updateUser(
        @PathVariable userId: Long,
        @RequestBody userUpdate: UserUpdate,
    ): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body("dDD")
    }

}