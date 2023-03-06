package ru.itmo.cookingservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.cookingservice.dto.UserDto
import ru.itmo.cookingservice.models.User
import ru.itmo.cookingservice.services.UserService

@RestController
@RequestMapping("users")
class UserController(val userService: UserService) {

    @GetMapping()
    fun getAll(): Iterable<User>{
        return userService.getAll()
    }

    @PostMapping()
    fun create(@RequestBody userDto: UserDto): User{
        return userService.create(userDto.name, userDto.password, userDto.email)
    }


}