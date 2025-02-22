package com.example.app.controller

import com.example.app.service.UserService
import com.example.app.util.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun create(@RequestBody authRequest: AuthRequest): AuthResponse {
        val authentication = UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        authenticationManager.authenticate(authentication)
        return AuthResponse(jwtUtils.generateToken(authRequest.email))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: AuthRequest): ResponseEntity<String> {
        userService.registerUser(request.email, request.password)
        return ResponseEntity.ok("User registered successfully")
    }

    data class AuthResponse(
        val token: String
    )
}
