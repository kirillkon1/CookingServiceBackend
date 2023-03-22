package ru.itmo.cookingservice.receipt.category

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/receipts/categories")
class ReceiptCategoryController(val receptCategoryService: ReceptCategoryService) {

    @GetMapping()
    fun getAll(): List<ReceiptCategory> {
        return receptCategoryService.getAll()
    }

    @GetMapping("/search")
    fun search(@RequestParam(required = false, name = "name") name: String): List<ReceiptCategory> {
        return receptCategoryService.getStartsWith(name)
    }

    @GetMapping("/type")
    fun getByType(@RequestParam(required = false, name = "type") type: String): List<ReceiptCategory> {
        return receptCategoryService.getByType(type)
    }
}
