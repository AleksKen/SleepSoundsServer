package com.example.app.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class JWTUtils {
    @Autowired
    private lateinit var encoder: JwtEncoder

    fun generateToken(username: String): String {
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(username)
            .build()
        return encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}