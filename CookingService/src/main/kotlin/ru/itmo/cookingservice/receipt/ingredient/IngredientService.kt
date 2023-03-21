package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.stereotype.Service

@Service
class IngredientService(private val ingredientRepository: IngredientRepository) {

    fun getAll(): List<Ingredient> {
        return ingredientRepository.findAll()
    }

    fun doIngredientsSearch(name: String?, max: Int = 20, containing: Boolean = false): List<Ingredient> {
        val ingredientList: MutableList<Ingredient> = ingredientRepository.findByStartsWith(name!!)

        if (ingredientList.size >= max) {
            return ingredientList.take(max)
        }

        if (containing) {
            val tmpList = ingredientRepository.findByNameContains(name)
            ingredientList.addAll(tmpList)
        }

        return ingredientList.take(max).sortedBy { it.name }
    }
}
