package com.ahmadarif.oauthserver.config

import com.ahmadarif.oauthserver.config.oauth2.Oauth2LogoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource


/**
 * Created by ARIF on 03-Jun-17.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(val dataSource: DataSource, val oauth2LogoutHandler: Oauth2LogoutHandler) : WebSecurityConfigurerAdapter() {


//    @Autowired @Qualifier("dataSource")
//    lateinit var dataSource: DataSource

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(oauth2LogoutHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        val getUserByUsernameQuery = "select username, password, active as enabled from s_users where username=?"
        val getAuthoritiesByUsername = """
                select u.username, p.user_role
                from s_users u
                inner join s_permissions p on u.id = p.id_user
                where u.username = ?
                """

        auth
                .jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(passwordEncoder()) // password di database di encode
                .usersByUsernameQuery(getUserByUsernameQuery)
                .authoritiesByUsernameQuery(getAuthoritiesByUsername)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}