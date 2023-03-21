package ru.itmo.cookingservice.receipt.filter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.receipt.Receipt

@Repository
interface ReceiptFilterRepository : JpaRepository<Receipt, Long> {

//    @Query(
//        value = "select r from Receipt r where(r.compositions.)"
//    )
//    fun findByAllIngredientsIn(@Param("names") ingredientIds: List<String>)


    @Query(
        value = "select r from Receipt r join r.compositions c join c.ingredient i where i.name in :names group by r.id " +
                "having count(i.id) <= :size"
    )
    fun findByReceiptsByIngredientsIdsIn(@Param("names") names: List<String>, @Param("size") size: Int): List<Receipt>
}
