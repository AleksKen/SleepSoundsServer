package com.example.app.service

import com.example.app.model.User
import com.example.app.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Service
class CustomUserDetailsService : UserDetailsManager {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun createUser(userData: UserDetails) {
        val email = userData.username
        val password = passwordEncoder.encode(userData.password)
        val user = User(email = email, password = password)
        userRepository.save(user)
    }

    override fun updateUser(user: UserDetails) = throw UnsupportedOperationException("Unimplemented method 'updateUser'")
    override fun deleteUser(username: String) = throw UnsupportedOperationException("Unimplemented method 'deleteUser'")
    override fun changePassword(oldPassword: String, newPassword: String) = throw UnsupportedOperationException("Unimplemented method 'changePassword'")
    override fun userExists(username: String) = throw UnsupportedOperationException("Unimplemented method 'userExists'")
    override fun loadUserByUsername(email: String): UserDetails =
        userRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("User not found") }
}
