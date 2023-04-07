package ru.itmo.cookingservice.receipt.receiptDto.responseDto

import com.fasterxml.jackson.annotation.JsonProperty
import ru.itmo.cookingservice.receipt.category.ReceiptCategory

class ReceiptSimpleDto(
    val id: Long,

    val name: String,

    val rating: Int,

    val categories: List<ReceiptCategory>,

    @JsonProperty(value = "image_url")
    val imageUrl: String? = null

)
