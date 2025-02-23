package com.example.app.model

import jakarta.persistence.*

@Entity
@Table(name = "playlists")
data class Playlist(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var imagePath: String,

    @Column(nullable = false)
    var name: String,

    var category: String,

    @Column(nullable = false)
    var genre: String,

    @ManyToMany(mappedBy = "playlists")
    var users: MutableSet<User> = mutableSetOf(),

    @ManyToMany
    var songs: MutableSet<Song> = mutableSetOf()

) : BaseEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Playlist) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
