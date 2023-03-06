package ru.itmo.cookingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookingServiceApplication

fun main(args: Array<String>) {
	runApplication<CookingServiceApplication>(*args)
}
