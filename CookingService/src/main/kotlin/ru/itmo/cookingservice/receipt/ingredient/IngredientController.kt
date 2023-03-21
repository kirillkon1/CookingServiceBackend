package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/ingredients")
class IngredientController(val ingredientService: IngredientService) {

    @GetMapping
    fun getAll(): List<Ingredient> {
        return ingredientService.getAll()
    }

    @GetMapping("/search")
    fun getStartsWith(
        @RequestParam(required = true, name = "name") name: String? = null,
        @RequestParam(required = false, name = "max") max: Int? = null,
        @RequestParam(required = false, name = "contains") contain: Boolean? = null,
    ): List<Ingredient> {
        return ingredientService.doIngredientsSearch(name = name, max = max ?: 20, containing = contain ?: false)
    }
}
