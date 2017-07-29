package com.ahmadarif.oauthserver.config.oauth2

import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.http.HttpServletResponse
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest


/**
 * Created by ARIF on 03-Jun-17.
 */

@Component
class Oauth2LogoutHandler @Autowired
constructor(@Lazy val consumerTokenServices: ConsumerTokenServices) : LogoutSuccessHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onLogoutSuccess(req: HttpServletRequest, res: HttpServletResponse, a: Authentication) {
        val token = req.getParameter("token")
        if (token != null) {
            consumerTokenServices.revokeToken(token)
        }
        val redirect = req.getParameter("redirect")
        if (redirect != null) {
            res.sendRedirect(redirect)
        }
    }
}