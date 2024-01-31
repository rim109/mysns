package com.example.letter.domain.letter.controller

import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.letter.service.LetterService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LetterController(
    private val letterService: LetterService
) {

    @Operation(summary = "letter 단건 조회")
    @GetMapping("/letter/{letterId}")
    fun getLetter(
        @PathVariable letterId: Long
    ): ResponseEntity<LetterResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(letterService.getLetter(letterId))
    }

    @Operation(summary = "letter 삭제")
    @DeleteMapping("/letter/{letterId}")
    fun deleteLetter(
        @PathVariable letterId: Long
    ): ResponseEntity<Unit> {
        letterService.deleteLetter(letterId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}