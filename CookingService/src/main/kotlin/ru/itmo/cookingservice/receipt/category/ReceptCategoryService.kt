package ru.itmo.cookingservice.receipt.category

import org.springframework.stereotype.Service


@Service
class ReceptCategoryService(val receiptCategoryRepository: ReceiptCategoryRepository) {

    fun getAll(): MutableList<ReceiptCategory> {
        return receiptCategoryRepository.findAll()
    }

    fun getStartsWith(starts: String): List<ReceiptCategory> {
        return receiptCategoryRepository.findAllByNameContaining(starts)
    }

    fun getByType(type: String): List<ReceiptCategory> {
        return try {
            receiptCategoryRepository.findAllByType(ReceiptCategoryType.valueOf(type.uppercase()))
        } catch (e: Exception) {
            receiptCategoryRepository.findAllByType(ReceiptCategoryType.OTHER)

        }
    }
}
