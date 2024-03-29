package com.example.letter.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class SignupRequest(

    @field:NotBlank(message = "닉네임은 반드시 작성해주세요")
    @Min(3)
    val nickname: String,

    @field:NotBlank(message = "이메일은 반드시 작성해주세요")
    @field:Email(message = "이메일 형식으로 작성해주세요")
    val email: String,

    @field:Length(min = 4, max = 15, message = "비밀번호는 4자 이상, 15자 이하여야합니다.")
    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{4,15}\$",
        message = "영문, 숫자, 특수문자를 포함한 4~15자리로 입력해주세요"
    )
    val password: String,

    val passwordConfirm: String,

    @field: NotBlank
    @field: Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)로 입력해주세요"
    )
    val birthDate: String,

    @field: NotBlank
    @field: Pattern(
        regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
        message = "전화 번호 형식으로 작성해주세요"
    )
    val phoneNumber: String,
    val info: String,
    val role: String
)
