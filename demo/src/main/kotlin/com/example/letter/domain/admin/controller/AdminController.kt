package com.example.letter.domain.admin.controller

import com.example.letter.domain.admin.service.AdminService
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/admins")
@RestController
class AdminController(
    private val adminService: AdminService
) {

    @Operation(summary = "letter 전체 조회")
    @GetMapping("/letter/{letterId}")
    fun getListLetter(
        @PathVariable letterId: Long
    ): ResponseEntity<List<LetterResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.getLetterList())
    }

    @Operation(summary = "letter 수정")
    @PatchMapping("/letter/{letterId}")
    fun adminUpdateLetter(
        @PathVariable letterId: Long,
        @RequestBody request: LetterRequest
    ): ResponseEntity<LetterResponse> {
        adminService.adminDeleteLetter(letterId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.adminUpdateLetter(letterId, request))
    }

    @Operation(summary = "letter 삭제")
    @DeleteMapping("/letter/{letterId}")
    fun adminDeleteLetter(
        @PathVariable letterId: Long
    ): ResponseEntity<Unit> {
        adminService.adminDeleteLetter(letterId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}