package com.CodeElevate.ExpenseTracker.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.stereotype.Component; // Import annotation

@Component
public class JwtUtil {

    // Secret key used for signing, in Base64 encoding
    private static final String SECRET_KEY = "yourbase64encodedsecretkey";

    // Method to create a JWT token
    public String createToken(String username) {
        // Decode the base64 secret key and create a SecretKey
        byte[] bytes = java.util.Base64.getDecoder().decode(SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(bytes);

        // Create JWT token using the SecretKey
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Set expiration (1 day)
                .signWith(key, SignatureAlgorithm.HS256) // Sign the JWT with the key and algorithm
                .compact();
    }

    // Method to verify the JWT token
    public boolean verifyToken(String token) {
        try {
            // Decode the base64 secret key and create a SecretKey
            byte[] bytes = java.util.Base64.getDecoder().decode(SECRET_KEY);
            SecretKey key = Keys.hmacShaKeyFor(bytes);

            // Verify the JWT token
            Jwts.parserBuilder()
                    .setSigningKey(key) // Set the key for verification
                    .build()
                    .parseClaimsJws(token); // Parse the JWT and verify the signature

            return true; // If no exception, the token is valid
        } catch (Exception e) {
            // Handle verification errors
            return false;
        }
    }
}
