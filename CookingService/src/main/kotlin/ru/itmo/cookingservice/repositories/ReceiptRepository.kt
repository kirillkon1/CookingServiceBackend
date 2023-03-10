package ru.itmo.cookingservice.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.itmo.cookingservice.models.Receipt

interface ReceiptRepository : JpaRepository<Receipt, Long> {

    override fun findAll(pageable: Pageable): Page<Receipt>

    @Query(
        value = "select * from receipt where (select starts_with(name, ?1));",
        nativeQuery = true,
    )
    fun getStartsWith(@Param("str") str: String): List<Receipt>
}
