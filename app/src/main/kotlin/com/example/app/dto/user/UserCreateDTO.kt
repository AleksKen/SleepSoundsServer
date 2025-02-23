package com.example.app.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class UserCreateDTO(
    @field:Email
    var email: String = "",

    var firstName: String = "",
    var lastName: String = "",

    @field:Size(min = 3)
    @field:NotBlank
    var password: String = "",

    var playlistIds: MutableSet<Long> = mutableSetOf()
)