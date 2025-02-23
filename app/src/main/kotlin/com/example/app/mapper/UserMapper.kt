package com.example.app.mapper

import com.example.app.dto.user.UserCreateDTO
import com.example.app.dto.user.UserDTO
import com.example.app.model.User
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder


@Mapper(
    uses = [JsonNullableMapper::class, ReferenceMapper::class],
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
abstract class UserMapper {
    @Autowired
    protected lateinit var passwordEncoder: PasswordEncoder

    abstract fun map(user: User): UserDTO
    abstract fun map(dto: UserCreateDTO): User

    @BeforeMapping
    fun encryptPassword(data: UserCreateDTO) {
        data.password = passwordEncoder.encode(data.password)
    }
}