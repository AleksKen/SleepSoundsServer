package com.example.app.dto.user


data class UserDTO(
    var id: Long? = null,
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var playlistIds: MutableSet<Long>
)