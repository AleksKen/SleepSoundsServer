package com.example.app.mapper

import com.example.app.dto.user.UserCreateDTO
import com.example.app.dto.user.UserDTO
import com.example.app.model.Playlist
import com.example.app.model.User
import com.example.app.repository.PlaylistRepository
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import java.util.stream.Collectors


@Mapper(
    uses = [JsonNullableMapper::class, ReferenceMapper::class],
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class UserMapper {
    @Autowired
    protected lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    protected lateinit var playlistRepository: PlaylistRepository

    @Mapping(source = "playlists", target = "playlistIds", qualifiedByName = ["getPlaylistIds"])
    abstract fun map(user: User): UserDTO
    @Mapping(source = "playlistIds", target = "playlists", qualifiedByName = ["getPlaylists"])
    abstract fun map(dto: UserCreateDTO): User

    @BeforeMapping
    fun encryptPassword(data: UserCreateDTO) {
        data.password = passwordEncoder.encode(data.password)
    }

    @Named("getPlaylistIds")
    fun getPlaylistIds(playlists: Set<Playlist>): Set<Long> {
        return playlists.stream()
            .map<Long>(Playlist::id)
            .collect(Collectors.toSet())
    }

    @Named("getPlaylists")
    fun getPlaylists(playlistIds: Set<Long>): Set<Playlist> {
        return playlistRepository.findAll()
            .stream()
            .filter { playlist ->
                Optional.ofNullable<Set<Long>>(playlistIds)
                    .orElse(emptySet())
                    .contains(playlist.id)
            }
            .collect(Collectors.toSet())
    }
}