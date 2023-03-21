package ru.itmo.cookingservice.receipt.rating

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.receipt.Receipt
import java.util.Optional

@Repository
interface ReceiptRatingRepository : JpaRepository<ReceiptRating, Long> {


    @Query(
        value = "UPDATE Receipt r SET r.rating = " +
                "(SELECT SUM(rr.rating)/COUNT(*) FROM ReceiptRating rr WHERE rr.receipt.id = :id)" +
                "WHERE r.id = :id"
    )
    @Modifying
    fun updateRatingByReceiptAndRating(@Param("id") receiptId: Long)
    fun getByUserAndReceipt(user: User, receipt: Receipt): Optional<ReceiptRating>
}