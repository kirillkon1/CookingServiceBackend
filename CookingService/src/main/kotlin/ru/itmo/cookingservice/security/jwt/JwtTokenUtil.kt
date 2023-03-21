package ru.itmo.cookingservice.security.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import java.util.*

class JwtTokenUtil {


    @Value("\${spring.security.jwt.secret}")
    private val secretKey = "secret"
    @Value("\${spring.security.jwt.expiration}")
    private val expiration = 86400000 // 24 hours

    fun generateToken(username: String): String {
        val claims = Jwts.claims().setSubject(username)
        val now = Date()
        val expirationDate = Date(now.time + expiration)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }
    }
}