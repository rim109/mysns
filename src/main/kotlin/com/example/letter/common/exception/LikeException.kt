package com.example.letter.common.exception

data class LikeException(val id: Any, val userId: Long) : RuntimeException(
    "이 아이디($id)로는 찜 할 수 없습니다."
)