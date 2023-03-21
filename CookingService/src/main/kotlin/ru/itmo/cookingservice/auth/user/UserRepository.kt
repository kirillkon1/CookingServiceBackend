package ru.itmo.cookingservice.auth.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findUserByName(name: String): Optional<User>
    fun findUserByNameAndPassword(name: String, password: String): Optional<User>
}
