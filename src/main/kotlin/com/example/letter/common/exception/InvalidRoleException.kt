package com.example.letter.common.exception

data class InvalidRoleException(val role: String) : RuntimeException(
    "invalid ROLE = $role USER OR ADMIN"
)
