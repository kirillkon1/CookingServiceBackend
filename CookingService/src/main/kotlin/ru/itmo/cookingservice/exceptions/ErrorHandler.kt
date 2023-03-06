package ru.itmo.cookingservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.itmo.cookingservice.dto.ApiError
import ru.itmo.cookingservice.exceptions.userException.UserAlreadyExistsException
import ru.itmo.cookingservice.exceptions.userException.UserDoesNotExistException

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val error = exception.fieldErrors[0].defaultMessage
        val apiError = ApiError(error)
        return ResponseEntity<ApiError>(apiError, null, HttpStatus.BAD_REQUEST)
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
}
