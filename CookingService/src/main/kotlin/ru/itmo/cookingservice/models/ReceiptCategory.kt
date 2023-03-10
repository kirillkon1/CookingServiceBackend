package ru.itmo.cookingservice.models

import jakarta.persistence.*

@Table
@Entity
class ReceiptCategory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false, unique = true)
    var name: String? = null,

)
