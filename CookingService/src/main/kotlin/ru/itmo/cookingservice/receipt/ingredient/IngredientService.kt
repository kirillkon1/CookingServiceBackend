package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class IngredientService(private val ingredientRepository: IngredientRepository) {

    fun getAll(): List<Ingredient> {
        return ingredientRepository.findAll()
    }

    fun getById(id: Long): Ingredient?{
        return ingredientRepository.findByIdOrNull(id)
    }

    fun doIngredientsSearch(name: String?, max: Int = 20, containing: Boolean = false): List<Ingredient> {
        val ingredientList: MutableList<Ingredient> = ingredientRepository.findByStartsWith(name!!)

        if (ingredientList.size >= max) {
            return ingredientList.take(max).sortedBy { it.name }
        }

        if (containing) {
            val tmpList = ingredientRepository.findByNameContains(name)
            return tmpList.take(max).sortedBy { it.name }
        }

        return ingredientList.take(max).sortedBy { it.name }
    }


// TODO: Добавить createIngredient(ingredientDTO)
}
