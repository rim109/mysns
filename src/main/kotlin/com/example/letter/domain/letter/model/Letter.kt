package com.example.letter.domain.letter.model

import com.example.letter.common.model.BaseTime
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "letter")
class Letter(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: User,

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