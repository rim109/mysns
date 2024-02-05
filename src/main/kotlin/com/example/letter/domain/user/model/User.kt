package com.example.letter.domain.user.model

import com.example.letter.common.exception.EmailExistException
import com.example.letter.common.exception.NicknameExistException
import com.example.letter.common.model.BaseTime
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.repository.UserRepository
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password",nullable = false)
    var password: String,

    @Column(name = "birthDate",nullable = false)
    var birthDate: String,

    @Column(name = "phoneNumber",nullable = false)
    var phoneNumber: String,

    @Column(name = "info",nullable = false)
    var info: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    var role: UserRole,


    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        birthDate = birthDate,
        phoneNumber = phoneNumber,
        info = info,
        role = role.name,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun checkingEmailAndNicknameExists(email: String, nickname: String, userRepository: UserRepository) {
    if (userRepository.existsByEmail(email)) {
        throw EmailExistException(email)
    }

    if (userRepository.existsByNickname(nickname)) {
        throw NicknameExistException(nickname)
    }
}