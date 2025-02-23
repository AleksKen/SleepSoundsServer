package com.example.app.mapper

import com.example.app.model.BaseEntity
import jakarta.persistence.EntityManager
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.TargetType
import org.springframework.beans.factory.annotation.Autowired

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
abstract class ReferenceMapper {
    @Autowired
    private lateinit var entityManager: EntityManager

    fun <T : BaseEntity> toEntity(id: Long?, @TargetType entityClass: Class<T>): T? =
        id?.let { entityManager.find(entityClass, id) }
}