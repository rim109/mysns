package com.example.letter.domain.letter.repository

import com.example.letter.domain.letter.model.Letter
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long> {
}