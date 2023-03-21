package ru.itmo.cookingservice.auth.user.exceptions

class UserDoesNotExistException(override val message: String?) : Exception(message)
