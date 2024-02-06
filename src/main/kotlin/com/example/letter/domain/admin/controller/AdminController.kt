package com.example.letter.domain.admin.controller

import com.example.letter.common.exception.dto.BaseResponse
import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.admin.service.AdminService
import com.example.letter.domain.letter.dto.LetterPageResponse
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.user.dto.UserPageResponse
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.dto.UserUpdate
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Sort
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
    @Operation(summary = "letter 전체 조회")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/letters")
    fun getLetterList(
    ): ResponseEntity<List<LetterResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.getLetterList())
    }

    @Operation(summary = "letter 전체 페이지 네이션 조회")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/letters/page")
    fun getLetterPage(
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        @RequestParam(defaultValue = "createdAt") sort: String?,
        @RequestParam direction: Sort.Direction
    ): ResponseEntity<LetterPageResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.getLetterPage(pageNumber, pageSize, sort, direction))
    }

    @Operation(summary = "letter 수정 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/letters/{letterId}")
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
    @DeleteMapping("/letters/{letterId}")
    fun adminDeleteLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        adminService.adminDeleteLetter(letterId, userId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @Operation(summary = "user 전체 조회 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{userId}")
    fun getUserList(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserList())
    }

    @Operation(summary = "user 전체 페이지 네이션 조회 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/page")
    fun getUserPage(
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        @RequestParam(defaultValue = "createdAt") sort: String?,
        @RequestParam direction: Sort.Direction
    ): ResponseEntity<UserPageResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(adminService.getUserPage(pageNumber, pageSize, sort, direction))
    }

    @Operation(summary = "user 등급 변경 ONLY ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{userId}")
    fun updateUser(
        @PathVariable userId: Long,
        @RequestBody userUpdate: UserUpdate,
    ): BaseResponse<UserUpdate> {
        val resultMsg: String = adminService.updateUser(userId, userUpdate)
        return BaseResponse(message = resultMsg)
    }

}