package com.example.app.controller

import com.example.app.dto.song.SongCreateDTO
import com.example.app.dto.song.SongDTO
import com.example.app.service.SongService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.Long

@RestController
@RequestMapping("/api/songs")
class SongController {
    @Autowired
    private lateinit var songService: SongService

    @GetMapping
    fun index(): ResponseEntity<List<SongDTO>> {
        val res = songService.index()
        return ResponseEntity.ok()
            .header("X-Total-Count", res.size.toString())
            .body(res)
    }

    @GetMapping(path = ["/{id}"])
    fun show(@PathVariable id: Long): SongDTO {
        return songService.show(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: @Valid SongCreateDTO): SongDTO {
        return songService.create(dto)
    }
}