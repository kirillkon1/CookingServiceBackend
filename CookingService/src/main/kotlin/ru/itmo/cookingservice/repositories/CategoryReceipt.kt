package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.models.ReceiptCategory

@Repository
interface CategoryReceipt : JpaRepository<ReceiptCategory, Long>
