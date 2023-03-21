package ru.itmo.cookingservice.receipt

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.receipt.category.ReceiptCategory

@Repository
interface ReceiptRepository : JpaRepository<Receipt, Long> {

    override fun findAll(pageable: Pageable): Page<Receipt>

    @Query(
        value = "select * from receipt where (select starts_with(name, ?1));",
        nativeQuery = true,
    )
    fun getStartsWith(@Param("str") str: String): List<Receipt>

    @Query(
        value = "select * from receipt where (receipt.name ilike concat('%', :str, '%') )",
        nativeQuery = true,
    )
    fun getByNameContains(@Param("str") str: String): List<Receipt>

    fun getReceiptsByCategoriesIn(categories: MutableCollection<MutableList<ReceiptCategory>>): List<Receipt>

    @Query(
        value = "select r from Receipt r join r.categories c where c.name in :names",
    )
    fun getReceiptsByCategoriesNamesIn(@Param("names") names: List<String?>): List<Receipt>

    @Query(
        value = "select r from Receipt r join r.categories c where c.id in :ids",
    )
    fun getReceiptsByCategoriesIdsIn(@Param("ids") ids: List<Long>): List<Receipt>
}
