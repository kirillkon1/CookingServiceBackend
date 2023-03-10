package ru.itmo.cookingservice.exceptions.userException

class UserDoesNotExistException(override val message: String?) : Exception(message)
