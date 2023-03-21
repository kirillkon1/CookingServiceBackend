package ru.itmo.cookingservice.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.itmo.cookingservice.security.UserDetailsServiceImpl
import ru.itmo.cookingservice.security.jwt.JwtAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: UserDetailsServiceImpl) {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(userService)
    }

//    @Throws(Exception::class)
//    @Bean
//    fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(userService)
//    }

    @Bean
    fun httpSecurityConfig(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/auth/**").permitAll()
                auth.requestMatchers("/test/**").permitAll()
                auth.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                auth.requestMatchers("/api/**").hasAuthority("USER")
                auth.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                auth.anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


}
