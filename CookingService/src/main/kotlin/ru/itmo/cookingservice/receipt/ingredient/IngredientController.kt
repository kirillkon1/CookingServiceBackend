package ru.itmo.cookingservice.receipt.ingredient

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/ingredients")
class IngredientController(val ingredientService: IngredientService) {

    @GetMapping
    fun getAll(): List<Ingredient> {
        return ingredientService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Ingredient? {
        return ingredientService.getById(id)
    }

    @GetMapping("/search")
    fun search(
        @RequestParam(required = true, name = "name") name: String? = " ",
        @RequestParam(required = false, name = "max") max: Int? = null,
        @RequestParam(required = false, name = "contains") contain: Boolean? = null,
    ): List<Ingredient> {
        return ingredientService.doIngredientsSearch(name = name, max = max ?: 20, containing = contain ?: false)
    }
}
