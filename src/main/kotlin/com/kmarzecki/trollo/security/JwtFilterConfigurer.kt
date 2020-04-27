package com.kmarzecki.trollo.security

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Configurer adding a Jwt token filter to the filter chain
 */
class JwtFilterConfigurer(private val jwtTokenService: JwtTokenService) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(JwtTokenFilter(jwtTokenService), UsernamePasswordAuthenticationFilter::class.java)
    }

}