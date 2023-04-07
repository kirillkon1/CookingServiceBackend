package ru.itmo.cookingservice.receipt.ingredient

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.itmo.cookingservice.receipt.Status

@Entity
@Table(name = "ingredients")
class Ingredient(

    @Column(nullable = false, unique = true)
    val name: String? = null,

    @JsonIgnore
    val createdBy: String? = null,

    @JsonIgnore
    val status: Status

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
