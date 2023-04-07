package ru.itmo.cookingservice.auth.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class UserDtoRequest : Serializable {
    @NotNull
    @NotBlank
    val name: String? = null

    @NotNull
    @NotBlank
    val password: String? = null

    @Email
    val email: String? = null
}
