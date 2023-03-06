package ru.itmo.cookingservice.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.cookingservice.models.Receipt

interface ReceiptRepository : JpaRepository<Receipt, Long> {

    override fun findAll(pageable: Pageable): Page<Receipt>
}
