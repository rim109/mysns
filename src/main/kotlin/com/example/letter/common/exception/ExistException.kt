package com.example.letter.common.exception

data class EmailExistException(val nickname: String) : RuntimeException(
    "이미 존재하는 이메일입니다."
)

data class NicknameExistException(val nickname: String) : RuntimeException(
    "이미 존재하는 닉네임입니다."
)