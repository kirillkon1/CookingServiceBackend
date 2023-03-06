package ru.itmo.cookingservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Table(name = "user_roles")
@Entity
class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id", nullable = false, updatable = false)
    val id: Long,

    @Column(name = "name", unique = true, nullable = false)
    var name: String? = null,
)
