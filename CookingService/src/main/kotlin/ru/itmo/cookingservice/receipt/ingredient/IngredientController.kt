package ru.itmo.cookingservice.receipt.ingredient

import jakarta.validation.constraints.Min
import org.springframework.web.bind.annotation.*
import ru.itmo.cookingservice.receipt.receiptDto.requestDto.IngredientDto
import ru.itmo.cookingservice.utils.Logger
import javax.validation.Valid

@RestController
@RequestMapping("api/ingredients")
@CrossOrigin(origins = ["http://localhost:3000"])
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
        @Valid @Min(1) @RequestParam(required = false, name = "total") total: Int?,
        @RequestParam(required = false, name = "contains") contain: Boolean? = null,
    ): List<Ingredient> {
        Logger.info("name=$name, total=$total, contains=$contain")
        return ingredientService.doIngredientsSearch(name = name, total = total ?: 20, containing = contain ?: false)
    }

    @PostMapping
    fun create(dto: IngredientDto): Ingredient{
        return ingredientService.create(dto)
    }
}
