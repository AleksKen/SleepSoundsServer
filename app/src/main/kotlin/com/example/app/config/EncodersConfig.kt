package com.example.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey


@Configuration
class EncodersConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun rsaKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)  // Установите размер ключа, например 2048 бит
        return keyPairGenerator.generateKeyPair()
    }

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk = RSAKey.Builder(rsaKeyPair().public as RSAPublicKey).privateKey(rsaKeyPair().private as RSAPrivateKey).build()
        return NimbusJwtEncoder(ImmutableJWKSet(JWKSet(jwk)))
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
       return NimbusJwtDecoder.withPublicKey(rsaKeyPair().public as RSAPublicKey).build()
    }

}