package com.example.letter.domain.letter.model

import com.example.letter.common.model.BaseTime
import com.example.letter.domain.letter.dto.LetterResponse
import jakarta.persistence.*
import org.hibernate.envers.Audited

@Entity
@Table(name = "letter")
@Audited
class Letter(
    @Column(name = "nickname") var nickname: String,
    @Column(name = "content") var content: String


) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}

fun Letter.toResponse(): LetterResponse {
    return LetterResponse(
        id = id!!,
        nickname = nickname,
        content = content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )

}