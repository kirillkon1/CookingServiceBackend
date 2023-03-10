package ru.itmo.cookingservice.models

import jakarta.persistence.*

@Table(name = "compositions")
@Entity
class Composition(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column
    val amount: Double = 1.0,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ingredient_id", nullable = false)
    val ingredient: Ingredient? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "metrics_id", nullable = false)
    val metric: Metric? = null,

)
