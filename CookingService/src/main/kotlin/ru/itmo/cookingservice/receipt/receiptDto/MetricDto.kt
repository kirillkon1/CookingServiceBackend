package ru.itmo.cookingservice.receipt.receiptDto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

class MetricDto : Serializable {
    @NotBlank(message = "Название Метрики не может быть пустым!")
    @NotNull(message = "Название Метрики не может быть null!")
    val name: String? = null
}
