package com.example.app.controller

import com.example.app.dto.user.UserDTO
import com.example.app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun index(): ResponseEntity<List<UserDTO>> {
        val res = userService.index()
        return ResponseEntity.ok()
            .body(res)
    }

    @GetMapping(path = ["/{id}"])
    fun show(@PathVariable id: Long): UserDTO {
        return userService.show(id)
    }

    @PostMapping("/{userId}/playlists/{playlistId}")
    fun addPlaylistToUser(
        @PathVariable userId: Long,
        @PathVariable playlistId: Long,
    ): ResponseEntity<String> {
        userService.addPlaylistToUser(userId, playlistId)
        return ResponseEntity.ok("Playlist added to user")
    }

    @DeleteMapping("/{userId}/playlists/{playlistId}")
    fun removePlaylistFromUser(
        @PathVariable userId: Long,
        @PathVariable playlistId: Long,
    ): ResponseEntity<String> {
        userService.removePlaylistFromUser(userId, playlistId)
        return ResponseEntity.ok("Playlist removed from user")
    }
}