package com.example.app.model

import jakarta.persistence.*

@Entity
@Table(name = "songs")
data class Song(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var artist: String,

    @Column(nullable = false)
    var duration: Int,

    @Column(nullable = false)
    var filePath: String,

    @ManyToMany
    var playlists: MutableSet<Playlist> = mutableSetOf()

) : BaseEntity
