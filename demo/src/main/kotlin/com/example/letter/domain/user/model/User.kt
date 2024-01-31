package com.example.letter.domain.user.model

import com.example.letter.common.model.BaseTime
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var birthDate: String,

    @Column(nullable = false)
    var phoneNumber: String,

    @Column(nullable = false)
    var info: String,

    @Column(nullable = false)
    var role: String,


    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}