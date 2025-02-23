package com.example.app.mapper

import com.example.app.dto.song.SongCreateDTO
import com.example.app.dto.song.SongDTO
import com.example.app.model.Song
import org.mapstruct.*


@Mapper(
    uses = [JsonNullableMapper::class, ReferenceMapper::class],
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class SongMapper {
    abstract fun map(song: Song): SongDTO
    abstract fun map(dto: SongCreateDTO): Song
}