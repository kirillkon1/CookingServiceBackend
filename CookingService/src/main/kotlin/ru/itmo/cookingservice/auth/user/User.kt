package ru.itmo.cookingservice.auth.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import ru.itmo.cookingservice.auth.user.userRole.UserRole
import java.time.Instant

@Table(name = "users")
@Entity
class User(

    @Column(name = "name", nullable = false, unique = true)
    @JsonProperty(value = "username")
    var name: String? = null,

    @Column(name = "password", nullable = false)
    @JsonIgnore
    var password: String? = null,

    @Column(name = "email", nullable = true)
    @JsonIgnore
    var email: String? = null,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_usersrole",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
    )
    @JsonIgnore
    var roles: MutableSet<UserRole> = mutableSetOf(),

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

//    @JsonProperty("create_date")
    @JsonIgnore
    @Column(name = "create_date")
    @field:CreationTimestamp
    var createDate: Instant? = null

    //    @JsonProperty("last_modify_date")
    @JsonIgnore
    @Column(name = "last_modify_date")
    @field:UpdateTimestamp
    var lastModifyDate: Instant? = null

}
