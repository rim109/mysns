package com.example.letter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class LetterApplication

fun main(args: Array<String>) {
	runApplication<LetterApplication>(*args)
}
