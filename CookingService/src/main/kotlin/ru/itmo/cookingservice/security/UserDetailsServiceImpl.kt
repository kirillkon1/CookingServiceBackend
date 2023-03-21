package ru.itmo.cookingservice.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.itmo.cookingservice.auth.user.UserService

@Service
class UserDetailsServiceImpl(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getByUsername(username)
        return UserDetailsImpl(user)
    }
}