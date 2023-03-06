package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.cookingservice.models.User
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findUserByName(name: String): Optional<User>
}
