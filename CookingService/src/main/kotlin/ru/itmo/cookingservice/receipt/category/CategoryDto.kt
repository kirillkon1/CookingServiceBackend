package ru.itmo.cookingservice.receipt.category

import java.io.Serializable

class CategoryDto : Serializable {
    val searchById: Boolean = false
    val categories: List<SimpleCategoryDto>? = null

    class SimpleCategoryDto : Serializable {
        val id: Long? = null
        val name: String? = null
    }
}
