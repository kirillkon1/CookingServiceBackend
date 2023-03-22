package ru.itmo.cookingservice.receipt.category

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ReceiptCategoryRepository : JpaRepository<ReceiptCategory, Long> {

    fun findByName(name: String): Optional<ReceiptCategory>

    fun findAllByNameContaining(name: String): List<ReceiptCategory>

    fun findAllByType(type: ReceiptCategoryType): List<ReceiptCategory>
}
