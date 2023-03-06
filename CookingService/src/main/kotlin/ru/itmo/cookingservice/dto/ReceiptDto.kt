package ru.itmo.cookingservice.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.time.LocalDateTime

data class ReceiptDto(

    @NotNull(message = "Название рецепта не может быть null")
    @NotBlank(message = "Название рецепта не может быть пустым")
    val name: String? = null,

    val description: String? = null,
    val amountOfPortions: Int = 1,
    val calories: Int = 0,

    val categories: MutableList<ReceiptCategoryDto>? = null,

    @NotEmpty(message = "Список composition не может быть пустым!")
    @NotNull(message = "Поле composition не может быть null!")
    val composition: MutableList<CompositionDto>? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) : Serializable {

    data class ReceiptCategoryDto(
        @NotBlank(message = "Название Категории не может быть пустым!")
        @NotNull(message = "Название Категории не может быть null!")
        val name: String
    ) : Serializable

    data class CompositionDto(
        @NotNull(message = "Поле Ингредиента не может быть null")
        val ingredient: IngredientDto? = null,
        @NotNull(message = "Поле Метрики не может быть null")
        val metric: MetricDto? = null,
        @Min(0, message = "Кол-во ингредиентов не может быть меньше нуля!")
        @Max(999, message = "Подозрительно большое количесвто ингредиентов!")
        val amount: Double = 1.0,
    ) : Serializable {
        data class IngredientDto(
            @NotBlank(message = "Название Ингредиента не может быть пустым!")
            @NotNull(message = "Название Ингредиента не может быть null!")
            val name: String? = null
        ) : Serializable

        data class MetricDto(
            @NotBlank(message = "Название Метрики не может быть пустым!")
            @NotNull(message = "Название Метрики не может быть null!")
            val name: String? = null
        ) : Serializable
    }
}
