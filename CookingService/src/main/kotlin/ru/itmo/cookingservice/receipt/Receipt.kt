package ru.itmo.cookingservice.receipt

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.receipt.composition.Composition
import ru.itmo.cookingservice.receipt.category.ReceiptCategory
import java.time.Instant
import java.util.*

@Entity
@Table
class Receipt(
    @Column(nullable = false)
    var name: String? = null,

    @Column
    var description: String? = null,

    @Column(name = "amount_of_portions")
    var amountOfPortions: Int = 1,

    @Column
    var calories: Int = 0,

    @Lob
    @Column(name="picture")
    var picture: ByteArray? = null,

    @JoinColumn(name = "user_id")
    @JsonProperty("owner")
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var user: User? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "receipt_receipt_category",
        joinColumns = [JoinColumn(name = "receipt_id", referencedColumnName = "id")],
    )
    var categories: MutableList<ReceiptCategory>? = null,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    var compositions: MutableList<Composition>? = null,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column
    var rating: Int = 0

    @JsonProperty("create_date")
    @Column(name = "create_date")
    @field:CreationTimestamp
    var createDate: Instant? = null

    @JsonProperty("last_modify_date")
    @Column(name = "last_modify_date")
    @field:UpdateTimestamp
    var lastModifyDate: Instant? = null
}
