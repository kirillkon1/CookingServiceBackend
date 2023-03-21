package ru.itmo.cookingservice.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class RegisterDto {

    @NotBlank(message = "login не может быть пустым!")
    @Size(min = 3, message = "login не может быть короче 3 символов!")
    val login: String? = null

    @NotBlank(message = "password не может быть пустым!")
    @Size(min = 3, message = "password не может быть короче 3 символов!")
    val password: String? = null

    @Email(message = "email не корректен!")
    val email: String? = null

}