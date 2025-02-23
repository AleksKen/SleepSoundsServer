package com.example.app.dto.song

import com.example.app.model.Playlist
import jakarta.validation.constraints.NotBlank

data class SongCreateDTO (
    @NotBlank
    var title: String,

    @NotBlank
    var artist: String,

    @NotBlank
    var duration: Int,

    @NotBlank
    var filePath: String,

    var playlists: MutableSet<Playlist> = mutableSetOf()
)