package com.example.letter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LetterApplication

fun main(args: Array<String>) {
	runApplication<LetterApplication>(*args)
}
