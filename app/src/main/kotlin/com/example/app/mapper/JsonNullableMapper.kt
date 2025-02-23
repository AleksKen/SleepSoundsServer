package com.example.app.mapper

import org.mapstruct.Condition
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.openapitools.jackson.nullable.JsonNullable

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
abstract class JsonNullableMapper {
    fun <T> wrap(entity: T): JsonNullable<T> = JsonNullable.of(entity)

    fun <T> unwrap(jsonNullable: JsonNullable<T>?): T? = jsonNullable?.orElse(null)

    @Condition
    fun <T> isPresent(jsonNullable: JsonNullable<T>?): Boolean = jsonNullable?.isPresent ?: false
}
