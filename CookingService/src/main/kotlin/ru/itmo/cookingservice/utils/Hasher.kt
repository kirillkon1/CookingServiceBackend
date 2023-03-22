package ru.itmo.cookingservice.utils

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class Hasher {

    companion object {
        fun hash(obj: String): String {
            val bytes = obj.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }

        fun sha256(input: String): String {
            val bytes = input.toByteArray(StandardCharsets.UTF_8)
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }
    }

}
