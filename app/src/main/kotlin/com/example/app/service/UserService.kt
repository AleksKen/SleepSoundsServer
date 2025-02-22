package com.example.app.service

import com.example.app.model.User
import com.example.app.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun registerUser(email: String, password: String): User {
        if (userRepository.findByEmail(email).isPresent) {
            throw IllegalArgumentException("User already exists")
        }
        val hashedPassword = passwordEncoder.encode(password)
        return userRepository.save(User(email = email, password = hashedPassword))
    }

    fun findByEmail(email: String): Optional<User> = userRepository.findByEmail(email)
}