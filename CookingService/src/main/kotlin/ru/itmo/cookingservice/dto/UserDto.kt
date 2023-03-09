package ru.itmo.cookingservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class UserDto : Serializable {
    @NotNull
    @NotBlank
    val name: String? = null

    @NotNull
    @NotBlank
    val password: String? = null

    @Email
    val email: String? = null
}
