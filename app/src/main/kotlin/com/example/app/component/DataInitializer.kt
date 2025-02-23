package com.example.app.component

import com.example.app.model.Playlist
import com.example.app.model.Song
import com.example.app.repository.PlaylistRepository
import com.example.app.repository.SongRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class DataInitializer(
    private val songRepository: SongRepository,
    private val playlistRepository: PlaylistRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        // Создаём песни
        val song1 = Song(
            title = "Next Level",
            artist = "Aespa",
            duration = 171,
            filePath = "/Users/mariakonasova/Desktop/Songs/aespa-NextLevel.mp3"
        )
        val song2 = Song(
            title = "Aenergy",
            artist = "Aespa",
            duration = 147,
            filePath = "/Users/mariakonasova/Desktop/Songs/aespa-aenergy.mp3"
        )
        val song3 = Song(
            title = "Better Things",
            artist = "Aespa",
            duration = 203,
            filePath = "/Users/mariakonasova/Desktop/Songs/aespa-Better_Things.mp3"
        )
        val song4 = Song(
            title = "Drama",
            artist = "Aespa",
            duration = 215,
            filePath = "/Users/mariakonasova/Desktop/Songs/aespa-Drama.mp3"
        )
        val song5 = Song(
            title = "Illusion",
            artist = "Aespa",
            duration = 195,
            filePath = "/Users/mariakonasova/Desktop/Songs/aespa-Illusion.mp3"
        )
        val savedSong1 = songRepository.save(song1)
        val savedSong2 = songRepository.save(song2)
        val savedSong3 = songRepository.save(song3)
        val savedSong4 = songRepository.save(song4)
        val savedSong5 = songRepository.save(song5)

        // Создаём плейлисты
        val playlist1 = Playlist(
            name = "Pop Hits",
            imagePath = "https://drive.google.com/uc?export=view&id=1AaAT_TTcHveXU66yaF1_G0i39elaaO5G",
            category = "Ambient",
            genre = "Dance",
            songs = mutableSetOf(savedSong1, savedSong2)
        )
        val playlist2 = Playlist(
            name = "Rock Classics",
            imagePath = "https://drive.google.com/uc?export=view&id=1aEJXMPzScATePQrTxugkAy_Di2t_TdMI",
            category = "For Kids",
            genre = "Classic Rock",
            songs = mutableSetOf(savedSong2, savedSong3)
        )
        val playlist3 = Playlist(
            name = "Rock Classics",
            imagePath = "https://drive.google.com/uc?export=view&id=1Gdz6cW6oEeU2o6xaLkE5HLQqO4V_YUTT",
            category = "Ambient",
            genre = "Classic Rock",
            songs = mutableSetOf(savedSong2, savedSong4, savedSong5)
        )
        playlistRepository.save(playlist1)
        playlistRepository.save(playlist2)
        playlistRepository.save(playlist3)
    }
}