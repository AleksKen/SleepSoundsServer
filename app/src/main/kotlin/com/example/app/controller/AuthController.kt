package com.example.app.controller

import com.example.app.dto.user.UserCreateDTO
import com.example.app.service.UserService
import com.example.app.util.JWTUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

data class AuthRequest(val email: String, val password: String)

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private lateinit var jwtUtils: JWTUtils

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userService: UserService

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun create(@RequestBody authRequest: AuthRequest): AuthResponse {
        logger.warn(authRequest.password)
        val authentication = UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        authenticationManager.authenticate(authentication)
        val user = userService.findUserByEmail(authRequest.email)
        return AuthResponse(user.id, user.email, user.firstName, user.lastName, jwtUtils.generateToken(authRequest.email))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: UserCreateDTO): AuthResponse {
        val userDTO = userService.registerUser(request)
        return AuthResponse(userDTO.id, userDTO.email, userDTO.firstName, userDTO.lastName, jwtUtils.generateToken(userDTO.email))
    }

    data class AuthResponse(
        val id: Long?,
        val email: String,
        val firstName: String,
        val lastName: String,
        val token: String
    )
}
