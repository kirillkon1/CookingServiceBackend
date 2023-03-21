package ru.itmo.cookingservice.exceptions

class NotFoundException(override val message: String?) : Exception(message)
