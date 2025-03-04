package com.example.app.repository

import com.example.app.model.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    @EntityGraph(attributePaths = ["playlists"])
    override fun findById(userId: Long): Optional<User>
}
