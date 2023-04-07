package ru.itmo.cookingservice.auth.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.cookingservice.auth.user.dto.UserDtoRequest

@RestController
@RequestMapping("api/users")
class UserController(val userService: UserService) {

    @GetMapping()
    fun getAll(): Iterable<User> {
        return userService.getAll()
    }

    @PostMapping()
    fun create(@RequestBody userDto: UserDtoRequest): User {
        return userService.create(userDto.name, userDto.password, userDto.email)
    }
}
