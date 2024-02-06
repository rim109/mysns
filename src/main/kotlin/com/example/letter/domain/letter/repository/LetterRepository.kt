package com.example.letter.domain.letter.repository

import com.example.letter.domain.letter.model.Letter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface LetterRepository : JpaRepository<Letter, Long> {
    @EntityGraph(attributePaths = ["user"])
    override fun findAll(pageable: Pageable): Page<Letter>

    @EntityGraph(attributePaths = ["user"])
    override fun findAll(sort: Sort): MutableList<Letter>
}