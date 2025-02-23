package com.example.app.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    var email: String,

    var firstName: String = "",

    var lastName: String = "",

    @Column(nullable = false)
    private var password: String,

    @ManyToMany
    var playlists: MutableSet<Playlist> = mutableSetOf(),


) : UserDetails, BaseEntity {
    override fun getAuthorities(): Collection<GrantedAuthority> = listOf()
    override fun getPassword() = password
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
