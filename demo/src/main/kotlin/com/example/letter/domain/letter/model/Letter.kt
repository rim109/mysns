package com.example.letter.domain.letter.model

import com.example.letter.domain.letter.dto.LetterResponse
import jakarta.persistence.*

@Entity
@Table(name = "letters")
class Letter(
    @Column(name = "nickname") var nickname: String,
    @Column(name = "content") var content: String


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}

fun Letter.toResponse(): LetterResponse {
    return LetterResponse(
        id = id!!,
        nickname = nickname,
        content = content
    )

}