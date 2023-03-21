package ru.itmo.cookingservice.receipt.category

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/receipts/categories")
class ReceiptCategoryController(val receptCategoryService: ReceptCategoryService) {

    @GetMapping()
    fun getAll(): List<ReceiptCategory> {
        return receptCategoryService.getAll()
    }
}
