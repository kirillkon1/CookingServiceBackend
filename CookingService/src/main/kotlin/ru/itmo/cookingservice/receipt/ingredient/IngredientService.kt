package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.itmo.cookingservice.receipt.Status
import ru.itmo.cookingservice.receipt.receiptDto.requestDto.IngredientDto

@Service
class IngredientService(private val ingredientRepository: IngredientRepository) {

    fun getAll(): List<Ingredient> {
        return ingredientRepository.findAll()
    }

    fun getById(id: Long): Ingredient? {
        return ingredientRepository.findByIdOrNull(id)
    }

    fun doIngredientsSearch(name: String?, total: Int = 20, containing: Boolean = false): List<Ingredient> {
        val ingredientList: MutableList<Ingredient> = ingredientRepository.findByStartsWith(name!!)

        if (ingredientList.size >= total) {
            return ingredientList.take(total).sortedBy { it.name }
        }

        if (containing) {
            val tmpList = ingredientRepository.findByNameContains(name)
            return tmpList.take(total).sortedBy { it.name }
        }

        return ingredientList.take(total).sortedBy { it.name }
    }

    fun create(dto: IngredientDto): Ingredient {
        val currentUsername = SecurityContextHolder.getContext().authentication.name!!
        return ingredientRepository.save(
            Ingredient(
                name = dto.name,
                createdBy = currentUsername,
                status = Status.ON_MODERATION
            )
        )
    }


}
