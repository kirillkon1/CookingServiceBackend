package ru.itmo.cookingservice.auth

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.cookingservice.auth.dto.JwtResponse
import ru.itmo.cookingservice.auth.dto.LoginDto
import ru.itmo.cookingservice.auth.dto.RegisterDto
import ru.itmo.cookingservice.utils.Logger

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @GetMapping("/valid")
    fun valid(): Boolean {
        return true
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody registerDto: RegisterDto): JwtResponse {
        Logger.info("[User ${registerDto.username}] - [POST: /auth/register]")
        return authService.register(registerDto)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginDto: LoginDto): JwtResponse {
        Logger.info("User ${loginDto.username} - [POST: /auth/login]")
        return authService.login(loginDto)
    }

}
