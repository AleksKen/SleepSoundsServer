package com.example.app.dto.playlist

import jakarta.validation.constraints.NotBlank

data class PlaylistCreateDTO(
    var imagePath: String,

    @NotBlank
    var name: String,

    var category: String,

    @NotBlank
    var genre: String,

    var songIds: MutableSet<Long> = mutableSetOf(),

    var userIds: MutableSet<Long> = mutableSetOf()
)