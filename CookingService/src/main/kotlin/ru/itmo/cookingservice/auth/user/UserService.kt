package ru.itmo.cookingservice.auth.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.auth.user.exceptions.UnauthorizedException
import ru.itmo.cookingservice.auth.user.exceptions.UserAlreadyExistsException
import ru.itmo.cookingservice.auth.user.exceptions.UserDoesNotExistException
import ru.itmo.cookingservice.auth.user.userRole.UserRoleRepository
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository, private val userRoleRepository: UserRoleRepository) {

    fun getAll(): Iterable<User> = userRepository.findAll()

    fun getByUsername(name: String): User {
        val userOpt = userRepository.findUserByName(name)

        if (userOpt.isEmpty) throw UserDoesNotExistException("Пользователя с именем $name не существует!")

        return userOpt.get()
    }

    fun getByNameAndPassword(name: String, password: String): User {

        val userOpt = userRepository.findUserByNameAndPassword(name, password)

        if (userOpt.isEmpty) throw UnauthorizedException(message = "Неверный логин или пароль!")

        return userOpt.get()
    }

    @Transactional
    fun create(name: String?, password: String?, email: String? = null): User {
        val userOpt: Optional<User> = userRepository.findUserByName(name!!)
        if (userOpt.isPresent) throw UserAlreadyExistsException("Пользователь с именем $name уже существует!")

        val userRole = userRoleRepository.findByName("USER")

        val user = User(name = name, password = password, email = email)
        user.roles.add(userRole)

        return userRepository.save(user)
    }

    fun update(id: Long, name: String? = null, password: String? = null, email: String? = null) {
        val userOpt: Optional<User> = userRepository.findById(id)

        if (userOpt.isEmpty) throw UserDoesNotExistException("Пользователя с именем $name не существует!")

        val user: User = userOpt.get()
        user.name = name ?: userOpt.get().name
        user.password = password ?: userOpt.get().password
        user.email = email ?: userOpt.get().email

        userRepository.save(user)
    }

    fun delete(id: Long) {
        val userOpt: Optional<User> = userRepository.findById(id)
        if (userOpt.isEmpty) throw UserDoesNotExistException("Пользователя с id $id не существует!")

        userRepository.deleteById(id)
    }
}
