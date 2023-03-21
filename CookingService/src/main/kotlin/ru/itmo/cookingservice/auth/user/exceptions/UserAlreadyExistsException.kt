package ru.itmo.cookingservice.auth.user.exceptions

class UserAlreadyExistsException(override val message: String?) : Exception(message)
