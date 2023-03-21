package ru.itmo.cookingservice.receipt.category

import org.springframework.stereotype.Service


@Service
class ReceptCategoryService(val receiptCategoryRepository: ReceiptCategoryRepository) {

    fun getAll(): MutableList<ReceiptCategory> {
        return receiptCategoryRepository.findAll()
    }

    fun getStartsWith(starts: String) {
    }
}
