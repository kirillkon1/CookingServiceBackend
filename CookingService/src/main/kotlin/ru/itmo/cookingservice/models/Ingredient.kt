package ru.itmo.cookingservice.models

import jakarta.persistence.*

@Entity
@Table(name = "ingredients")
class Ingredient(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false, unique = true)
    val name: String? = null,

)
