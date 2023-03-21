package ru.itmo.cookingservice.receipt.receiptDto

import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.time.LocalDateTime

class ReceiptDto {
    @NotNull(message = "Название рецепта не может быть null!")
    @NotBlank(message = "Название рецепта не может быть пустым!")
    val name: String? = null

    @NotEmpty(message = "Описание не может быть пустым")
    val description: String? = null

    @Min(0, message = "Кол-во порций не может быть меньше или равно нулю!")
    @Max(999, message = "Подозрительно большое количество порций!")
    val amountOfPortions: Int? = null

    @Min(0, message = "Кол-во калорий не может быть меньше нуля!")
    @Max(99999, message = "Подозрительно большое количество калорий!")
    val calories: Int? = null

    @Valid
    val categories: MutableList<ReceiptCategoryDto>? = null

    @NotEmpty(message = "Список compositions не может быть пустым!")
    @Valid
    val compositions: MutableList<CompositionDto>? = null

    val createdDate: LocalDateTime = LocalDateTime.now()
}
