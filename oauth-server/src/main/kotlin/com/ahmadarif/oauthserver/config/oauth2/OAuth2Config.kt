package com.ahmadarif.oauthserver.config.oauth2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.sql.DataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair




/**
 * Created by ARIF on 03-Jun-17.
 */
@Configuration
class OAuth2Config(
        val authenticationManager: AuthenticationManager,
        val dataSource: DataSource
) : AuthorizationServerConfigurerAdapter() {

//    @Autowired
//    @Qualifier("authenticationManagerBean")
//    lateinit var authenticationManager: AuthenticationManager

//    @Autowired @Qualifier("dataSource")
//    lateinit var dataSource: DataSource

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource)
    }

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(InMemoryTokenStore())
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()

        // jika mau menggunakan keysstore yang spesifik
//        val converter = JwtAccessTokenConverter()
//        val keyPair = KeyStoreKeyFactory(
//                ClassPathResource("keystore.jks"), "foobar".toCharArray()
//        ).getKeyPair("test")
//        converter.setKeyPair(keyPair)
//        return converter
    }
}