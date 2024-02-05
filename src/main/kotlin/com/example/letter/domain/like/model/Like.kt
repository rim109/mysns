package com.example.letter.domain.like.model

import com.example.letter.domain.letter.model.Letter
import com.example.letter.domain.like.dto.LikeResponse
import com.example.letter.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "likes")
class Like(
    @Column(name = "liked")
    var liked: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "letter_id")
    val letter: Letter,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: User


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

fun Like.toResponse(): LikeResponse {
    return LikeResponse(
        userId = user.id!!,
        letterId = letter.id!!,
        liked = liked
    )
}