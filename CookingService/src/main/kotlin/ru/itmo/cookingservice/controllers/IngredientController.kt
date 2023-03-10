package ru.itmo.cookingservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.itmo.cookingservice.models.Ingredient
import ru.itmo.cookingservice.repositories.IngredientRepository

@RestController
@RequestMapping("api/ingredients")
class IngredientController(val ingredientRepository: IngredientRepository) {

    @GetMapping
    fun getAll(): MutableList<Ingredient> {
        return ingredientRepository.findAll()
    }

    @GetMapping("/starts")
    fun getStartsWith(@RequestParam(required = true, name = "start") start: String?): List<Ingredient> {
        return ingredientRepository.getStartsWith(start!!)
    }
}
