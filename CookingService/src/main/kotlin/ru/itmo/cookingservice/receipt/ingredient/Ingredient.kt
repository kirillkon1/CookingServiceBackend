package ru.itmo.cookingservice.receipt.ingredient

import jakarta.persistence.*

@Entity
@Table(name = "ingredients")
class Ingredient(

    @Column(nullable = false, unique = true)
    val name: String? = null,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
