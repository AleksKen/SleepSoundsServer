package com.example.app.repository

import com.example.app.model.Song
import org.springframework.data.jpa.repository.JpaRepository

interface SongRepository: JpaRepository<Song, Long> {
}