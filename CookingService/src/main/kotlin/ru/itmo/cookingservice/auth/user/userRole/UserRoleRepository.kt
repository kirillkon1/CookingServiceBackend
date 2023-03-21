package ru.itmo.cookingservice.auth.user.userRole

import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository : JpaRepository<UserRole, Long> {

    fun findByName(name: String): UserRole
}
