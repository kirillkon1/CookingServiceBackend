package ru.itmo.cookingservice.exceptions.receiptExceptions

class NotFoundException(override val message: String?) : Exception(message)
