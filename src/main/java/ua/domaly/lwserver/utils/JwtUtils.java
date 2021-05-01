package ua.domaly.lwserver.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.domaly.lwserver.entity.User;

import java.util.Date;

import static java.lang.String.format;

/**
 * Utility class to work with JWT.
 */
@Log4j2
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    /**
     * Method to generate JWT.
     *
     * @param user {@link User}.
     * @return JWT.
     */
    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Method to get user id from JWT.
     *
     * @param token JWT.
     * @return id of user.
     */
    public String getUserId(String token) {
        final var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    /**
     * Method to get username from JWT.
     *
     * @param token JWT.
     * @return username of user.
     */
    public String getUsername(String token) {
        final var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    /**
     * Method to get expiration date from JWT.
     *
     * @param token JWT.
     * @return expiration date of JWT.
     */
    public Date getExpirationDate(String token) {
        final var claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }


    /**
     * Method to validate JWT.
     *
     * @param token JWT.
     * @return validation.
     */
    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - " + ex.getMessage());
        }
        return false;
    }
}

