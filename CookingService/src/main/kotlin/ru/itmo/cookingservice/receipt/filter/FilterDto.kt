package ru.itmo.cookingservice.receipt.filter

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import ru.itmo.cookingservice.receipt.ingredient.Ingredient
import ru.itmo.cookingservice.receipt.category.ReceiptCategory
import java.io.Serializable
import java.util.Date

class FilterDto : Serializable {

    val ingredientsList: List<Ingredient>? = null

    val categories: List<ReceiptCategory>? = null

    val page: Int? = null
    val size: Int? = null

    val ratingMin: Double? = null
    val ratingMax: Double? = null

    val dateAddFrom: Date? = null
    val dateAddTo: Date? = null

    val sortBy: SortType = SortType.RATING
    val sortDirection: SortDirection = SortDirection.ASC

    enum class SortType {
        RATING, AMOUNT_OF_RATINGS, VIEWS, DATE_ADDED, DATE_UPDATED
    }

    enum class SortDirection {
        ASC, DSC
    }

    class FilterIngredient {

        @NotNull(message = "[api/receipts/filter] ingredient.id не может быть null!")
        @Min(value = 1, message = "[api/receipts/filter] ingredient.id не может быть меньше 1!")
        val id: Long? = null

        val required: Boolean = true
    }
}
