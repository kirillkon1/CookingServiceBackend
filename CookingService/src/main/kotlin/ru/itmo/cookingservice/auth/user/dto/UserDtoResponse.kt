package ru.itmo.cookingservice.auth.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ru.itmo.cookingservice.auth.user.userRole.UserRole
import java.time.Instant

class UserDtoResponse(

    id: Long,
    username: String,
    email: String? = null,
    roles: List<UserRole>? = null,

    @JsonProperty(value = "create_date")
    createDate: Instant? = null,
    @JsonProperty(value = "last_modify_date")
    lastModifyDate: Instant? = null
)