package com.example.app.service

import com.example.app.dto.playlist.PlaylistCreateDTO
import com.example.app.dto.playlist.PlaylistDTO
import com.example.app.dto.song.SongDTO
import com.example.app.exception.ResourceNotFoundException
import com.example.app.mapper.PlaylistMapper
import com.example.app.mapper.SongMapper
import com.example.app.model.Playlist
import com.example.app.repository.PlaylistRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class PlaylistService {
    @Autowired
    private lateinit var playlistRepository: PlaylistRepository

    @Autowired
    private lateinit var playlistMapper: PlaylistMapper

    @Autowired
    private lateinit var songMapper: SongMapper


    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun index(): List<PlaylistDTO> {
        return playlistRepository.findAll().stream().map(playlistMapper::map).toList()
    }

    fun show(id: Long): PlaylistDTO {
        return playlistMapper.map(
            playlistRepository.findById(id)
                .orElseThrow { ResourceNotFoundException("Playlist with id $id not found!") })
    }

    fun create(dto: PlaylistCreateDTO): PlaylistDTO {
        logger.warn(dto.toString())
        val playlist: Playlist = playlistMapper.map(dto)
        logger.warn(playlist.toString())
        playlistRepository.save(playlist)
        return playlistMapper.map(playlist)
    }

    fun getSongsForPlaylist(playlistId: Long): List<SongDTO> {
        val playlist = playlistRepository.findById(playlistId)
            .orElseThrow { ResourceNotFoundException("Playlist with id $playlistId not found!") }

        return playlist.songs.stream().map(songMapper::map).toList()
    }
}