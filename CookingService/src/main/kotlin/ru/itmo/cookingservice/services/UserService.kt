package ru.itmo.cookingservice.services

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.cookingservice.exceptions.userException.UserAlreadyExistsException
import ru.itmo.cookingservice.exceptions.userException.UserDoesNotExistException
import ru.itmo.cookingservice.models.User
import ru.itmo.cookingservice.repositories.UserRepository
import ru.itmo.cookingservice.repositories.UserRoleRepository
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository, private val userRoleRepository: UserRoleRepository) {

    private val logger = KotlinLogging.logger {}

    fun getAll(): Iterable<User> = userRepository.findAll()

    @Transactional
    fun create(name: String?, password: String?, email: String? = null): User {
        logger.debug { "Receieved userDTO: name: $name; password: $password; email = $email" }

        val userOpt: Optional<User>? = name?.let { userRepository.findUserByName(it) }
        if (userOpt!!.isPresent) throw UserAlreadyExistsException("Пользователь с именем $name уже существует!")

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
