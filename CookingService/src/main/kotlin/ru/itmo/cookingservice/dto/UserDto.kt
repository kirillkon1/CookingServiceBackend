package ru.itmo.cookingservice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

data class UserDto(

    @NotNull
    @NotBlank
    val name: String,
    val password: String,
    val email: String? = null
) : Serializable