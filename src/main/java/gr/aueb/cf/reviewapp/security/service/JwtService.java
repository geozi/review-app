package gr.aueb.cf.reviewapp.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@link JwtService} class provides jwt-specific
 * services used during the authentication process.
 * @author geozi
 * @version 1
 */
@Component
@Slf4j
public class JwtService {

    private final String jwtSecret;

    public JwtService(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    // Auxiliary methods

    private SecretKey convertToKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(convertToKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Public methods

    /**
     * Extracts username from a JWT token.
     * @param token A JWT token of type String.
     * @return The username of type String.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generates a JWT token.
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The authenticated user details.
     * @return A JWT token of type String.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(convertToKey())
                .compact();
    }

    /**
     * Generates a JWT token.
     * @param userDetails The authenticated user details.
     * @return A JWT token of type String.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Validates a JWT token.
     * @param token The JWT token to be validated of type String.
     * @return True if it is a valid JWT token, false otherwise.
     */
    public boolean validateToken(String token) {
        boolean evalResult = false;
        try {
            Jwts.parser().verifyWith(convertToKey()).build().parseSignedClaims(token);
            evalResult = true;
        } catch(MalformedJwtException | IllegalArgumentException |
                ExpiredJwtException | UnsupportedJwtException e) {
            log.error(e.getMessage());
        }
        return evalResult;
    }

    /**
     * Extracts a JWT token from a http request.
     * @param request A http request
     * @return A JWT token of type String.
     */
    public String extractToken(HttpServletRequest request) {
        String token = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")) token = authHeader.substring(7);
        return token;
    }

}
