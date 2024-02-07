package com.example.letter.domain.letter.service

import com.example.letter.domain.letter.dto.DeleteLetterRequest
import com.example.letter.domain.letter.dto.LetterPageResponse
import com.example.letter.domain.letter.dto.LetterRequest
import com.example.letter.domain.letter.dto.LetterResponse
import org.springframework.data.domain.Sort

interface LetterService {

    fun getLetter(userId: Long, letterId: Long): LetterResponse

    fun getLetterPage(pageNumber: Int, pageSize: Int, sort: String?, direction: Sort.Direction): LetterPageResponse

    fun createLetter(userId: Long, request: LetterRequest): LetterResponse

    fun deleteLetter(userId: Long, letterId: Long, request: DeleteLetterRequest)
}