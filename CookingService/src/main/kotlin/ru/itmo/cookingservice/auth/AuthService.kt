package ru.itmo.cookingservice.auth

import org.springframework.stereotype.Service
import ru.itmo.cookingservice.auth.dto.JwtResponse
import ru.itmo.cookingservice.auth.dto.LoginDto
import ru.itmo.cookingservice.auth.dto.RegisterDto
import ru.itmo.cookingservice.auth.user.UserService
import ru.itmo.cookingservice.security.jwt.JwtTokenUtil
import ru.itmo.cookingservice.utils.Hasher

@Service
class AuthService(private val userService: UserService) {


    private val jwtTokenUtil = JwtTokenUtil()

    fun register(dto: RegisterDto): JwtResponse {

        val userName = dto.login
        val password = Hasher.sha256(dto.password!!)
        val email = dto.email

        val user = userService.create(name = userName, password = password, email = email)

        return JwtResponse(username = user.name!!, user.id, token = jwtTokenUtil.generateToken(user.name!!))
    }

    fun login(dto: LoginDto): JwtResponse {

        val encryptPassword = Hasher.sha256(dto.password!!)
        val user = userService.getByNameAndPassword(dto.login!!, encryptPassword)

        return JwtResponse(username = user.name!!, user.id, token = jwtTokenUtil.generateToken(user.name!!))
    }


}
