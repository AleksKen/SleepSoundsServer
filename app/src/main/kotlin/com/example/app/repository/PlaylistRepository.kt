package com.example.app.repository

import com.example.app.model.Playlist
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository: JpaRepository<Playlist, Long> {
}