package ru.itmo.cookingservice.receipt.metric

import jakarta.persistence.*

@Table(name = "metrics")
@Entity
class Metric(
    @Column(nullable = false, unique = true)
    val name: String? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
