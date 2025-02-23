package com.example.app.mapper

import com.example.app.dto.playlist.PlaylistCreateDTO
import com.example.app.dto.playlist.PlaylistDTO
import com.example.app.model.Playlist
import com.example.app.model.Song
import com.example.app.repository.SongRepository
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import java.util.stream.Collectors


@Mapper(
    uses = [JsonNullableMapper::class, ReferenceMapper::class],
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class PlaylistMapper {

    @Autowired
    protected lateinit var songRepository: SongRepository

    @Mapping(source = "songs", target = "songIds", qualifiedByName = ["getSongIds"])
    abstract fun map(playlist: Playlist): PlaylistDTO


    @Mapping(target = "songs", source = "songIds", qualifiedByName = ["getSongs"])
    abstract fun map(dto: PlaylistCreateDTO): Playlist

    @Named("getSongIds")
    fun getLabelIds(songs: Set<Song>): Set<Long> {
        return songs.stream()
            .map<Long>(Song::id)
            .collect(Collectors.toSet())
    }

    @Named("getSongs")
    fun getLabels(songIds: Set<Long>): Set<Song> {
        return songRepository.findAll()
            .stream()
            .filter { song ->
                Optional.ofNullable<Set<Long>>(songIds)
                    .orElse(emptySet())
                    .contains(song.id)
            }
            .collect(Collectors.toSet())
    }
}