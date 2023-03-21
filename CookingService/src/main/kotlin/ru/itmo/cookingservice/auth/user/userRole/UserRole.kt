package ru.itmo.cookingservice.auth.user.userRole

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Table(name = "user_roles")
@Entity
class UserRole(

    @Column(name = "name", unique = true, nullable = false)
    var name: String? = null,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:NotNull
    @JsonIgnore
    val id: Long = 0
}
