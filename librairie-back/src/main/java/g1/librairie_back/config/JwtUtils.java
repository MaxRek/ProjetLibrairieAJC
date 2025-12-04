package g1.librairie_back.config;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private final static String JWT_KEY = "6E5A7234753778214125442A472D4B6150645367556B58703273357638792F42";
    private final static long JWT_EXPIRATION = 3_600_000;

    private JwtUtils(){}

    public static String generate(Authentication auth){
        Date now = new Date();
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        return Jwts.builder()
            .subject(auth.getName())
            .issuedAt(now)
            .expiration(new Date(now.getTime() + JWT_EXPIRATION))
            .signWith(secretKey)
            .compact()
        ;
    }

    public static Optional<String> validateAndGetSubjet(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        try {
            String username = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject()
            ;
        
            return Optional.ofNullable(username);
        }

        catch (Exception ex){
            return Optional.empty();
        }



    }
}
