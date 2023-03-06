package ru.itmo.cookingservice.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import ru.itmo.cookingservice.dto.ReceiptDto
import java.time.LocalDateTime

@Entity
@Table
class Receipt(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    var name: String? = null,

    @Column
    var description: String? = null,

    @Column(name = "amount_of_portions")
    var amountOfPortions: Int = 1,

    @Column
    var calories: Int = 0,

    @Column
    var rating: Int = 0,

    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "receipt_receipt_category",
        joinColumns = [JoinColumn(name = "receipt_id", referencedColumnName = "id")],
    )
    var categories: MutableList<ReceiptCategory>? = null,

//    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @JoinTable(joinColumns = [JoinColumn(name = "receipt_id", referencedColumnName = "id")])
//    var composition: MutableList<Composition> = mutableListOf(),

    @OneToMany(mappedBy = "receipt", cascade = [CascadeType.ALL])
    var compositions: MutableList<Composition>? = null,

) {
    constructor(dto: ReceiptDto) : this() {
        this.name = dto.name
        this.description = dto.description
        this.amountOfPortions = dto.amountOfPortions
        this.calories = dto.calories
        this.rating = 0
        this.createdDate = dto.createdAt
    }
}