package ru.itmo.cookingservice.dto

import java.time.LocalDateTime

data class ApiError(val message: String? = "Unexpected error", val time: LocalDateTime = LocalDateTime.now()) {
}