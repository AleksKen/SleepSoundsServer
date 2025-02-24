package com.example.app.service

import com.example.app.dto.playlist.PlaylistDTO
import com.example.app.dto.user.UserCreateDTO
import com.example.app.dto.user.UserDTO
import com.example.app.exception.ResourceNotFoundException
import com.example.app.mapper.PlaylistMapper
import com.example.app.mapper.UserMapper
import com.example.app.model.User
import com.example.app.repository.PlaylistRepository
import com.example.app.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var playlistMapper: PlaylistMapper

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var playlistRepository: PlaylistRepository

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun registerUser(userCreateDTO: UserCreateDTO): UserDTO {
        logger.warn(userCreateDTO.toString())
        val user: User = userMapper.map(userCreateDTO)
        logger.warn(user.toString())
        userRepository.save(user)
        return userMapper.map(user)
    }

    fun findUserByEmail(email: String): UserDTO {
        return userMapper.map(
        userRepository.findByEmail(email)
            .orElseThrow{ ResourceNotFoundException("User with email $email not found!") })
    }

    fun addPlaylistToUser(userId: Long, playlistId: Long) {
        val user = userRepository.findById(userId).orElseThrow {
            ResourceNotFoundException("User with id $userId not found!")
        }
        val playlist = playlistRepository.findById(playlistId).orElseThrow {
            ResourceNotFoundException("Playlist with id $playlistId not found!")
        }
        user.playlists.add(playlist)
        playlist.users.add(user)
        userRepository.save(user)
        playlistRepository.save(playlist)
    }

    fun removePlaylistFromUser(userId: Long, playlistId: Long) {
        val user = userRepository.findById(userId).orElseThrow {
            ResourceNotFoundException("User with id $userId not found!")
        }
        val playlist = playlistRepository.findById(playlistId).orElseThrow {
            ResourceNotFoundException("Playlist with id $playlistId not found!")
        }

        user.playlists.remove(playlist)
        userRepository.save(user)
        playlist.users.remove(user)
        playlistRepository.save(playlist)
    }

    fun index(): List<UserDTO> {
        return userRepository.findAll().stream().map(userMapper::map).toList()
    }

    fun show(id: Long): UserDTO {
        return userMapper.map(
            userRepository.findById(id)
                .orElseThrow { ResourceNotFoundException("User with id $id not found!") })
    }

    fun getFavourites(userId: Long): List<PlaylistDTO> {
        val user = userRepository.findById(userId)
            .orElseThrow { ResourceNotFoundException("User with id $userId not found!") }

        return user.playlists.stream().map(playlistMapper::map).toList()
    }
}