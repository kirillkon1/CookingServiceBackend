package ru.itmo.cookingservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Table(name = "users")
@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @JsonIgnore
    val id: Long = 0,

    @Column(name = "name", nullable = false, unique = true)
    var name: String? = null,

    @Column(name = "password", nullable = false)
    @JsonIgnore
    var password: String? = null,

    @Column(name = "email", nullable = true)
    var email: String? = null,

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreatedDate
    var creationDate: LocalDateTime? = LocalDateTime.now(),

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_usersrole",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
    )
    var roles: MutableList<UserRole> = mutableListOf(),

)
