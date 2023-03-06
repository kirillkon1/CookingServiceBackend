package ru.itmo.cookingservice.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.cookingservice.models.UserRole

interface UserRoleRepository : JpaRepository<UserRole, Long> {

    fun findByName(name: String): UserRole
}
