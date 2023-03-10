package ru.itmo.cookingservice.services

import org.springframework.stereotype.Service
import ru.itmo.cookingservice.models.ReceiptCategory
import ru.itmo.cookingservice.repositories.ReceiptCategoryRepository

@Service
class ReceptCategoryService(val receiptCategoryRepository: ReceiptCategoryRepository) {

    fun getAll(): MutableList<ReceiptCategory> {
        return receiptCategoryRepository.findAll()
    }

    fun getStartsWith(starts: String) {
    }
}
