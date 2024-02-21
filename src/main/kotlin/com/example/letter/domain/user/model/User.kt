package com.example.letter.domain.user.model

import com.example.letter.common.exception.*
import com.example.letter.common.model.BaseTime
import com.example.letter.domain.letter.model.Letter
import com.example.letter.domain.letter.model.toResponse
import com.example.letter.domain.user.dto.UpdateUserRequest
import com.example.letter.domain.user.dto.UserResponse
import com.example.letter.domain.user.repository.UserRepository
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "birthDate", nullable = false)
    var birthDate: String,

    @Column(name = "phoneNumber", nullable = false)
    var phoneNumber: String,

    @Column(name = "info", nullable = false)
    var info: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole,

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val letters: MutableList<Letter> = mutableListOf(),

    @Column(nullable = false)
    val reported: Boolean


    ) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun deleteUser() {
        isDeleted = true
    }
    fun isReported(): Boolean {
        return this.reported
    }

    fun isSameMemberId(id: Long): Boolean {
        return this.id == id
    }

    fun updateUser(request: UpdateUserRequest){
        nickname = request.nickname
        phoneNumber = request.phoneNumber
        info = request.info
    }


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
        updatedAt = this.updatedAt,
        letter = letters.map { it.toResponse() }
    )
}

fun checkingEmailAndNicknameAndPhoneNumberExists(email: String, nickname: String, phoneNumber: String, userRepository: UserRepository) {
    if (userRepository.existsByEmail(email)) {
        throw EmailExistException(email)
    }

    if (userRepository.existsByNickname(nickname)) {
        throw NicknameExistException(nickname)
    }

    if (userRepository.existsByPhoneNumber(phoneNumber)) {
        throw PhoneNumberExistException(phoneNumber)
    }
}

fun passwordMisMatch(password: String, passwordConfirm: String) {
    if (password != passwordConfirm) throw PasswordMismatchException(password, passwordConfirm)
}

fun passwordNoHaveNickname(nickname: String, password: String) {
    val isExist = password.contains(nickname)
    if (isExist) throw PasswordNoHaveNicknameException(nickname, password)
}