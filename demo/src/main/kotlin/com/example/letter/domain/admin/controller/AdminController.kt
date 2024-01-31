package com.example.letter.domain.admin.controller

import com.example.letter.domain.admin.service.AdminService
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/admins")
@RestController
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping("/letter/{letterId}")
    fun getListLetter(
        @PathVariable letterId: Long
    ): ResponseEntity<List<LetterResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adminService.getLetterList())
    }

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