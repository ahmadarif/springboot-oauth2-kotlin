package com.ahmadarif.resourceserver.controller

import org.springframework.web.bind.annotation.CrossOrigin
import java.util.HashMap
import javax.servlet.http.HttpSession
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


/**
 * Created by ARIF on 03-Jun-17.
 */
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api")
class HaloController {

    companion object {
        private val STATE = "state"
    }

    @RequestMapping("/halo")
    fun halo(): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)
        hasil.put("page", "halo")
        return hasil
    }

    @RequestMapping("/admin")
    fun admin(user: Principal): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)
        hasil.put("page", "admin")
        hasil.put("user", user.name)
        return hasil
    }

    @RequestMapping("/client")
    fun client(user: Principal): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)
        hasil.put("page", "client")
        hasil.put("user", user.name)
        return hasil
    }

    @RequestMapping("/staff")
    fun staff(): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)
        hasil.put("page", "staff")
        return hasil
    }

    @RequestMapping("/state/new")
    fun newState(session: HttpSession): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)

        val state = UUID.randomUUID().toString()
        hasil.put(STATE, state)
        session.setAttribute(STATE, state)

        return hasil
    }

    @RequestMapping("/state/verify")
    fun verifyState(session: HttpSession): Map<String, Any> {
        val hasil = HashMap<String, Any>()
        hasil.put("sukses", true)

        val state = session.getAttribute(STATE) as String
        hasil.put(STATE, state)
        session.removeAttribute(STATE)

        return hasil
    }

}