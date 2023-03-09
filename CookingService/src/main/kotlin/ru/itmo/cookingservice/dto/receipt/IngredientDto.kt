package ru.itmo.cookingservice.dto.receipt

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class IngredientDto : Serializable {
    @NotBlank(message = "Название Ингредиента не может быть пустым!")
    @NotNull(message = "Название Ингредиента не может быть null!")
    val name: String? = null
}
