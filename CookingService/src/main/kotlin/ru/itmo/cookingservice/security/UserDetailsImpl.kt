package ru.itmo.cookingservice.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.itmo.cookingservice.auth.user.User

class UserDetailsImpl(private val user: User) : UserDetails {



    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles.stream().map { SimpleGrantedAuthority(it.name!!) }.toList()
    }

    fun getId(): Long{
        return user.id
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.name!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}