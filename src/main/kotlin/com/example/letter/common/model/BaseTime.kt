package com.example.letter.common.model

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH-mm"))
        protected set

    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    var updatedAt: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH-mm"))
        protected set

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false
}