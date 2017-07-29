package com.ahmadarif.resourceserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.core.annotation.Order
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.RemoteTokenServices


/**
 * Created by ARIF on 03-Jun-17.
 */
@Configuration
class Oauth2ResourceServer {

    companion object {
        private val RESOURCE_ID = "belajar"
    }

    @Configuration
    @Order(10)
    class NonOauthResources : WebSecurityConfigurerAdapter() {

        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http.authorizeRequests()
                    .antMatchers("/api/halo").permitAll()
                    .antMatchers("/api/state/**").permitAll()
                    .antMatchers("/**").permitAll()
                    .and().anonymous()
        }
    }

    @Configuration
    @EnableResourceServer
    class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

        override fun configure(resources: ResourceServerSecurityConfigurer) {
            val tokenService = RemoteTokenServices()
            tokenService.setClientId("clientimplicit")
            tokenService.setClientSecret("123")
            tokenService.setCheckTokenEndpointUrl("http://localhost:10000/oauth/check_token")

            resources
                    .resourceId(RESOURCE_ID)
                    .tokenServices(tokenService)
        }

        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http.authorizeRequests()
                    .antMatchers("/api/staff").hasRole("STAFF")
                    .antMatchers("/api/client").authenticated()
                    .antMatchers("/api/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
        }

    }

}