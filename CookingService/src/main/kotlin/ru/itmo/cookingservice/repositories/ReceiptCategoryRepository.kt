package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.cookingservice.models.ReceiptCategory
import java.util.Optional

interface ReceiptCategoryRepository : JpaRepository<ReceiptCategory, Long> {

    fun findByName(name: String): Optional<ReceiptCategory>
}
