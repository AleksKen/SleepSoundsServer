package com.example.app.controller


import com.example.app.dto.playlist.PlaylistCreateDTO
import com.example.app.dto.playlist.PlaylistDTO
import com.example.app.service.PlaylistService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/playlists")
class PlaylistController {
    @Autowired
    private lateinit var playlistService: PlaylistService

    @GetMapping
    fun index(): ResponseEntity<List<PlaylistDTO>> {
        val res = playlistService.index()
        return ResponseEntity.ok()
            .body(res)
    }

    @GetMapping(path = ["/{id}"])
    fun show(@PathVariable id: Long): PlaylistDTO {
        return playlistService.show(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: @Valid PlaylistCreateDTO): PlaylistDTO {
        return playlistService.create(dto)
    }
}