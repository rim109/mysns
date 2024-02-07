package com.example.letter.domain.like.controller

import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.like.dto.LikePageResponse
import com.example.letter.domain.like.dto.LikeResponse
import com.example.letter.domain.like.service.LikeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/likes")
@RestController
class LikeController(
    private val likeService: LikeService
) {
    @Operation(summary = "좋아요 기능")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/{letterId}")
    fun likeLetter(
        @PathVariable letterId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<LikeResponse> {
        val userId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.OK).body(likeService.likeLetter(letterId, userId))
    }

//    @Operation(summary = "좋아요 조회")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    @GetMapping
//    fun likeChecking(
//        @AuthenticationPrincipal userPrincipal: UserPrincipal
//    ): ResponseEntity<List<LikeResponse>> {
//        val userId = userPrincipal.id
//        return ResponseEntity.status(HttpStatus.OK).body(likeService.likeChecking(userId))
//    }

    @Operation(summary = "좋아요 조회")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    fun likeChecking(
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        @RequestParam(defaultValue = "createdAt") sort: String?,
        @RequestParam direction: Sort.Direction,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<LikePageResponse> {
        val userId = userPrincipal.id
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(likeService.likeChecking(userId, pageNumber, pageSize, sort, direction))
    }
}