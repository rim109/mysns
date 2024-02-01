package com.example.letter.domain.letter.controller

import com.example.letter.common.security.jwt.UserPrincipal
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.service.LetterService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
class LetterController(
    private val letterService: LetterService
) {

    @Operation(summary = "letter 단건 조회")
    @GetMapping("/letter/{letterId}")
    fun getLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long
    ): ResponseEntity<LetterResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(letterService.getLetter(letterId))
    }

    @Operation(summary = "letter 작성")
    @PostMapping("/letter/{letterId}")
    fun createLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: LetterRequest
    ): ResponseEntity<LetterResponse> {
        val userId = userPrincipal.id
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(letterService.createLetter(userId,request))
    }

    @Operation(summary = "letter 삭제")
    @DeleteMapping("/letter/{letterId}")
    fun deleteLetter(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable letterId: Long
    ): ResponseEntity<Unit> {
        letterService.deleteLetter(letterId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}