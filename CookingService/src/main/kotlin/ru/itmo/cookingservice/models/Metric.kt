package ru.itmo.cookingservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Table(name = "metrics")
@Entity
class Metric(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long? = 0,

    @Column(nullable = false, unique = true)
    val name: String? = null,
)
