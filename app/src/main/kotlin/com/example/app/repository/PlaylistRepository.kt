package com.example.app.repository

import com.example.app.model.Playlist
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PlaylistRepository: JpaRepository<Playlist, Long> {
    @EntityGraph(attributePaths = ["songs"])
    override fun findById(playlistId: Long): Optional<Playlist>
}