package com.ahmadarif.oauthserver.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.ModelMap


/**
 * Created by ARIF on 03-Jun-17.
 */
@Controller
class UserController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/user")
    @ResponseBody
    fun currentUser(auth: Authentication) = auth.principal

    @RequestMapping("/login")
    fun loginPage() {}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/home")
    fun homepage(currentUser: Authentication) = ModelMap("user", currentUser)

}