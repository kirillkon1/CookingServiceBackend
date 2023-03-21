package ru.itmo.cookingservice.receipt.rating

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

class RatingDto {

    @Min(1, message = "receiptId быть меньше 1!")
    @NotNull(message = "receptId не может быть null!")
    val receiptId: Long? = null


    @Min(1, message = "rating не может быть меньше 1!")
    @Max(5, message = "rating не может быть больше 5!")
    @NotNull(message = "rating не может быть null!")
    val rating: Long? = null


}