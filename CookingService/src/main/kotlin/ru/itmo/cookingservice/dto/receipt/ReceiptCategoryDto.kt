package ru.itmo.cookingservice.dto.receipt

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class ReceiptCategoryDto : Serializable {
    @NotBlank(message = "Название Категории не может быть пустым!")
    @NotNull(message = "Название Категории не может быть null!")
    val name: String? = null
}
