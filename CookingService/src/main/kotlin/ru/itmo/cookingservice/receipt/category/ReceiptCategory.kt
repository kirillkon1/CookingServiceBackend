package ru.itmo.cookingservice.receipt.category

import jakarta.persistence.*

@Table
@Entity
class ReceiptCategory(

    @Column(nullable = false, unique = true)
    var name: String? = null,

    @Enumerated(EnumType.STRING)
    var type: ReceiptCategoryType,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
