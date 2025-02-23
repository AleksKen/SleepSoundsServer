package com.example.app.service

import com.example.app.dto.song.SongCreateDTO
import com.example.app.dto.song.SongDTO
import com.example.app.mapper.SongMapper
import com.example.app.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.app.exception.ResourceNotFoundException
import com.example.app.model.Song
import org.slf4j.LoggerFactory

@Service
class SongService {
    @Autowired
    private lateinit var songRepository: SongRepository

    @Autowired
    private lateinit var songMapper: SongMapper


    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun index(): List<SongDTO> {
        return songRepository.findAll().stream().map(songMapper::map).toList()
    }

    fun show(id: Long): SongDTO {
        return songMapper.map(
            songRepository.findById(id)
                .orElseThrow { ResourceNotFoundException("Song with id $id not found!") })
    }

    fun create(dto: SongCreateDTO): SongDTO {
        logger.warn(dto.toString())
        val song: Song = songMapper.map(dto)
        logger.warn(song.toString())
        songRepository.save(song)
        return songMapper.map(song)
    }
}