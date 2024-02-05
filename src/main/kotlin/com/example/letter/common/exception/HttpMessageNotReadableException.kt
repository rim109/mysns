package com.example.letter.common.exception

class HttpMessageNotReadableException(
    val fieldName: String = "",
    message: String = "invalid ROLE !! USER OR ADMIN"
) : RuntimeException(message)