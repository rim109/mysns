package com.example.letter.domain.letter.controller

import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.letter.dto.DeleteLetterRequest
import com.example.letter.domain.letter.dto.LetterPageResponse
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.service.LetterService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
class LetterController(
    private val letterService: LetterService
) {
    @Operation(summary = "letter 전체 페이지 네이션 조회")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/letters")
    fun getLetterPage(
        @RequestParam(defaultValue = "1") pageNumber: Int,
        @RequestParam(defaultValue = "5") pageSize: Int,
        @RequestParam(defaultValue = "userId") sort: String?,
        @RequestParam direction: Sort.Direction
    ): ResponseEntity<LetterPageResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(letterService.getLetterPage(pageNumber, pageSize, sort, direction))
    }

    @Operation(summary = "letter 단건 조회")
    @PreAuthorize("#userPrincipal.id == #userId")
    @GetMapping("/users/{userId}/letters/{letterId}")
    fun getLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable userId: Long,
        @PathVariable letterId: Long
    ): ResponseEntity<LetterResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(letterService.getLetter(userId,letterId))
    }

    @Operation(summary = "letter 작성")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/users/{userId}/letters")
    fun createLetter(
        @PathVariable userId: Long,
        @Valid @RequestBody request: LetterRequest
    ): ResponseEntity<LetterResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(letterService.createLetter(userId, request))
    }

    @Operation(summary = "letter 삭제")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/letters/{letterId}")
    fun deleteLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long,
        @RequestBody request: DeleteLetterRequest
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        letterService.deleteLetter(userId, letterId, request)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}