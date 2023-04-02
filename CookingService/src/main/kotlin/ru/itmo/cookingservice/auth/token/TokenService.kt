package ru.itmo.cookingservice.auth.token

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.itmo.cookingservice.auth.user.User
import ru.itmo.cookingservice.auth.user.UserService

@Service
class TokenService(private val userService: UserService) {

    fun getUserFromToken(): User? {
        return userService.getByUsername(SecurityContextHolder.getContext().authentication.name)
    }


}