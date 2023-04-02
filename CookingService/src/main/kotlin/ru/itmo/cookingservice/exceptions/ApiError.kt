package ru.itmo.cookingservice.exceptions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ApiError(
    val message: String? = "Unexpected error",
    val time: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSSS")).toString()
)
