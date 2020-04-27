package com.kmarzecki.trollo.security

import com.kmarzecki.trollo.model.auth.Role
import com.kmarzecki.trollo.util.DateTimeProvider
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.AllArgsConstructor
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
@AllArgsConstructor
class JwtTokenService(
        val userDetailsService: UserDetailsServiceImpl,
        val dateProvider: DateTimeProvider
) {
    fun createToken(username: String?, set: Set<Role?>): String {
        val claims = Jwts.claims().setSubject(username)
        claims[ROLES] = set
        val now = dateProvider.currentDate()
        val validity = Date(now.time + VALID_TIME_MS)
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, ENCODED_SECRET_KEY)
                .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String?): String {
        return Jwts.parser().setSigningKey(ENCODED_SECRET_KEY).parseClaimsJws(token).body.subject
    }

    fun extractToken(req: HttpServletRequest): String? {
        return req.getHeader(TOKEN_HEADER_NAME)
    }

    fun extractToken(accessor: StompHeaderAccessor): String? {
        return accessor.getNativeHeader(TOKEN_HEADER_NAME)?.get(0)
    }

    fun validateToken(token: String?): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(ENCODED_SECRET_KEY).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: JwtException) {
            throw JwtException("Expired or invalid JWT token")
        } catch (e: IllegalArgumentException) {
            throw JwtException("Expired or invalid JWT token")
        }
    }

    companion object {
        private const val ROLES = "BASIC_ROLE"
        private const val SECRET_KEY = "NON_SAFE_SECRET"
        private val ENCODED_SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.toByteArray())
        private const val TOKEN_HEADER_NAME = "token"
        private const val VALID_TIME_MS = 3600000
    }
}