package ru.itmo.cookingservice.auth.user.exceptions

class UnauthorizedException (override val message: String?) : Exception(message)