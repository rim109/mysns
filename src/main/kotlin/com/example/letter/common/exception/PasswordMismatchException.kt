package com.example.letter.common.exception

data class PasswordMismatchException(val password: String, val passwordConfirm: String): RuntimeException(
    "입력하신 비밀번호와 다릅니다. 동일하게 입력해주시길 바랍니다!!"
)

data class PasswordNoHaveNicknameException(val nickname: String, val password: String): RuntimeException(
    "비밀번호를 입력 할 때 닉네임과 동일한 문장을 입력하지 마세요"
)
