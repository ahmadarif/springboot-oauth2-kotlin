package com.ahmadarif.oauthserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@SpringBootApplication
@EnableAuthorizationServer
class OauthServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(OauthServerApplication::class.java, *args)
}
