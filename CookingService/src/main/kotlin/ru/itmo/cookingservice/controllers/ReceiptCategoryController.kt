package ru.itmo.cookingservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.cookingservice.models.ReceiptCategory
import ru.itmo.cookingservice.services.ReceptCategoryService

@RestController
@RequestMapping("api/receipts/categories")
class ReceiptCategoryController(val receptCategoryService: ReceptCategoryService) {

    @GetMapping()
    fun getAll(): List<ReceiptCategory> {
        return receptCategoryService.getAll()
    }
}
