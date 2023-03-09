package ru.itmo.cookingservice.dto.receipt

import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class CompositionDto : Serializable {

    @NotNull(message = "Поле Ингредиента не может быть null")
    @Valid
    val ingredient: IngredientDto? = null

    @NotNull(message = "Поле Метрики не может быть null")
    @Valid
    val metric: MetricDto? = null

    @Min(0, message = "Кол-во ингредиентов не может быть меньше или равно нулю!")
    @Max(999, message = "Подозрительно большое количесвто ингредиентов!")
    val amount: Double = 1.0
}
