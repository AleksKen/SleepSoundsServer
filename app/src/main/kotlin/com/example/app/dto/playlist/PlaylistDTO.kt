package com.example.app.dto.playlist

data class PlaylistDTO(
    val id: Long? = null,

    var imagePath: String,

    var name: String,

    var category: String,

    var genre: String,

    var songIds: MutableSet<Long>
)