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
            filePath = "https://drive.google.com/uc?export=download&id=1YP7JxtuvZpAHSMv2S-4KEk8aZUkpw9le"
        )
        val song2 = Song(
            title = "Aenergy",
            artist = "Aespa",
            duration = 147,
            filePath = "https://drive.google.com/uc?export=download&id=1JRw2mpGhU2qadVdm2NR4HGYDiXXUS_0p"
        )
        val song3 = Song(
            title = "Better Things",
            artist = "Aespa",
            duration = 203,
            filePath = "https://drive.google.com/uc?export=download&id=1g8xwOxvRMj0nbOMiuYkrZouQATbA48WI"
        )
        val song4 = Song(
            title = "Drama",
            artist = "Aespa",
            duration = 215,
            filePath = "https://drive.google.com/uc?export=download&id=1y5HU42TQDFSs41Eqf9JyanScJ1GPFHuI"
        )
        val song5 = Song(
            title = "Illusion",
            artist = "Aespa",
            duration = 195,
            filePath = "https://drive.google.com/uc?export=download&id=1g-I8W5gpbU5_LoweECiW-FX3cDKaY-M6"
        )
        val savedSong1 = songRepository.save(song1)
        val savedSong2 = songRepository.save(song2)
        val savedSong3 = songRepository.save(song3)
        val savedSong4 = songRepository.save(song4)
        val savedSong5 = songRepository.save(song5)

        // Создаём плейлисты
        val playlist1 = Playlist(
            name = "Pop Hits",
            imagePath = "https://drive.google.com/uc?export=view&id=1zRu6yNMzwIa4d5Jk8XzTrEuZnbZRPAVR",
            category = "Ambient",
            genre = "Dance",
            songs = mutableSetOf(savedSong1, savedSong2)
        )
        val playlist2 = Playlist(
            name = "Rock Classics",
            imagePath = "https://drive.google.com/uc?export=view&id=1vF0GyPrh-q5ik6eAW1zAQq3OzPhWJQs1",
            category = "For Kids",
            genre = "Classic Rock",
            songs = mutableSetOf(savedSong2, savedSong3)
        )
        val playlist3 = Playlist(
            name = "Rock Classics",
            imagePath = "https://drive.google.com/uc?export=view&id=1oO6S-bxj97yU1Vw8ovXZBHJ6lo7sbMha",
            category = "Ambient",
            genre = "Classic Rock",
            songs = mutableSetOf(savedSong2, savedSong4, savedSong5)
        )
        playlistRepository.save(playlist1)
        playlistRepository.save(playlist2)
        playlistRepository.save(playlist3)
    }
}