package ru.itmo.cookingservice.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import ru.itmo.cookingservice.security.UserDetailsServiceImpl
import ru.itmo.cookingservice.utils.Logger


class JwtAuthenticationFilter(
    private val userService: UserDetailsServiceImpl,
) : OncePerRequestFilter() {

    private val jwtTokenUtil: JwtTokenUtil = JwtTokenUtil()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                val authToken = authHeader.substring(7)
                val username = jwtTokenUtil.getUsernameFromToken(authToken)

                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails = userService.loadUserByUsername(username)

                    if (jwtTokenUtil.validateToken(authToken)) {
                        val authentication =
                            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            }
        } catch (e: ExpiredJwtException) {
            Logger.warn("[" + request.method + "] " + e.message)
        } finally {
            filterChain.doFilter(request, response)
        }
    }


}
