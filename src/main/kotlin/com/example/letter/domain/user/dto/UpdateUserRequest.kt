package com.example.letter.domain.user.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class UpdateUserRequest(
    @field:NotBlank(message = "닉네임은 반드시 작성해주세요")
    @Min(3)
    val nickname: String,
    @field: NotBlank
    @field: Pattern(
        regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
        message = "전화 번호 형식으로 작성해주세요"
    )
    val phoneNumber: String,
    val info: String
)
