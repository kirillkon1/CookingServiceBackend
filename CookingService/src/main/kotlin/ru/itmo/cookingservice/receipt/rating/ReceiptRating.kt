package ru.itmo.cookingservice.receipt.rating

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.receipt.Receipt
import java.time.Instant

@Entity
@Table(name = "rating")
class ReceiptRating(

    @OneToOne
    val user: User? = null,

    @OneToOne
    val receipt: Receipt? = null,

    @Column(nullable = false)
    val rating: Long? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null


    @Column(name = "last_modify_date")
    @field:UpdateTimestamp
    var lastModifyDate: Instant? = null
}