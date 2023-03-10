package ru.itmo.cookingservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.itmo.cookingservice.exceptions.receiptExceptions.NotFoundException
import ru.itmo.cookingservice.exceptions.userException.UserAlreadyExistsException
import ru.itmo.cookingservice.exceptions.userException.UserDoesNotExistException

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ResponseEntity<List<String?>> {
        val errors = exception.fieldErrors.map {
            it.defaultMessage
        }.toList()

        return ResponseEntity<List<String?>>(
            errors,
            null,
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler
    fun userAlreadyExistsExceptionHandler(ex: UserAlreadyExistsException): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(ex.message),
            HttpStatus.CONFLICT,
        )
    }

    @ExceptionHandler
    fun userIsDoesNotExistExceptionHandler(ex: UserDoesNotExistException): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(ex.message),
            HttpStatus.NOT_FOUND,
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(ex: NotFoundException): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(ex.message),
            HttpStatus.NOT_FOUND,
        )
    }
}
