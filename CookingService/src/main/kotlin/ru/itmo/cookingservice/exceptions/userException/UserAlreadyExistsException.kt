package ru.itmo.cookingservice.exceptions.userException

class UserAlreadyExistsException(override val message: String?) : Exception(message)
