package com.example.letter.domain.letter.model

import com.example.letter.common.model.BaseTime
import com.example.letter.domain.letter.dto.LetterResponse
import com.example.letter.domain.like.model.Like
import com.example.letter.domain.user.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
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

    @Column(name = "content") var content: String,

    @Column(name = "num_liked") var numLike: Long = 0,

    @JsonIgnore
    @OneToMany(mappedBy = "letter", cascade = [CascadeType.REMOVE])
    val liked: List<Like> = mutableListOf(),


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
        numLike = liked.size,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        userId = user.id!!
    )
}