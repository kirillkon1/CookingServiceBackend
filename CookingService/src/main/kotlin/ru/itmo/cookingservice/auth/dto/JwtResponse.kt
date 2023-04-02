package ru.itmo.cookingservice.auth.dto

import com.fasterxml.jackson.annotation.JsonProperty

class JwtResponse(
    val username: String,
    @JsonProperty(value = "user_id")
    val userId: Long,
    val token: String
)