package ru.itmo.cookingservice.auth.dto

class JwtResponse(username: String, userId: Long, token: String) {

    val user: HashMap<String, Any> = hashMapOf()

    var token: String? = token

    init {
        this.user["username"] = username
        this.user["user_id"] = userId
    }
}