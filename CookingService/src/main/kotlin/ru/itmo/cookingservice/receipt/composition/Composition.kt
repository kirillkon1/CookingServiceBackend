package ru.itmo.cookingservice.receipt.composition

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.itmo.cookingservice.receipt.ingredient.Ingredient
import ru.itmo.cookingservice.receipt.metric.Metric

@Table(name = "compositions")
@Entity
class Composition(

    @Column
    val amount: Double = 1.0,

    @Column
    val description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ingredient_id", nullable = false)
    val ingredient: Ingredient? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "metrics_id", nullable = false)
    val metric: Metric? = null,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long = 0
}
