package com.example.app.dto.song

data class SongDTO (
    var id: Long? = null,

    var title: String,

    var artist: String,

    var duration: Int,

    var filePath: String,
)