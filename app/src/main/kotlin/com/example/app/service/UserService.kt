package com.example.app.service

import com.example.app.dto.user.UserCreateDTO
import com.example.app.dto.user.UserDTO
import com.example.app.exception.ResourceNotFoundException
import com.example.app.mapper.UserMapper
import com.example.app.model.User
import com.example.app.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Autowired
    private lateinit var userMapper: UserMapper

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun registerUser(userCreateDTO: UserCreateDTO): UserDTO {
        val user: User = userMapper.map(userCreateDTO)
        userRepository.save(user)
        return userMapper.map(user)
    }

    fun findUserByEmail(email: String): UserDTO {
        return userMapper.map(
        userRepository.findByEmail(email)
            .orElseThrow{ ResourceNotFoundException("User with email $email not found!") })
    }
}