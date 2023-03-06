package ru.itmo.cookingservice.test

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class TestDTO {
    @NotNull(message = "name is null")
    @NotBlank(message = "name is empty")
    val name: String? = null

    @NotNull(message = "pwd is null")
    @NotBlank(message = "pwd is empty")
    val pwd: String? = null
}