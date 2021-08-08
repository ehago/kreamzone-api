package com.ehago.kreamzone.config.auth;

import com.ehago.kreamzone.enumeration.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class JwtTokenProvider {

    static final String ACCESS_TOKEN_NAME = "access_token";
    static final String REFRESH_TOKEN_NAME = "refresh_token";
    static final int ACCESS_TOKEN_EXPIRATION_SECONDS = 60 * 30; // 30m
    static final int REFRESH_TOKEN_EXPIRATION_SECONDS = 60 * 60 * 24 * 7; // 7d

    private static final Key KEY;

    static {
        KEY = new SecretKeySpec(
                System.getenv("JWT_KEY").getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    private JwtTokenProvider() {
    }

    public static String createAccessToken(String name, String email, Role role) {
        return createToken(name, email, role, ACCESS_TOKEN_EXPIRATION_SECONDS);
    }

    public static String createRefreshToken(String name, String email, Role role) {
        return createToken(name, email, role, REFRESH_TOKEN_EXPIRATION_SECONDS);
    }

    private static String createToken(String name, String email, Role role, int expirationTime) {
        Map<String, Object> headers = getHeaders();
        Map<String, Object> payloads = getPayloads(email, name, role, expirationTime);

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    private static Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        return headers;
    }

    private static Map<String, Object> getPayloads(String email, String name, Role role, int expirationTime) {
        Date now = new Date();
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("iss", "kream.ga");
        payloads.put("sub", email);
        payloads.put("aud", name);
        payloads.put("role", role.getKey());
        payloads.put("iat", now);
        payloads.put("exp", new Date(now.getTime() + expirationTime));
        return payloads;
    }

    public static Jws<Claims> getClaims(String jwt) throws SignatureException, ExpiredJwtException, MalformedJwtException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
    }

    public static Authentication authenticate(String jwtToken) throws SignatureException, ExpiredJwtException, MalformedJwtException, IllegalArgumentException {
        Jws<Claims> claims = getClaims(jwtToken);
        Claims body = claims.getBody();
        String email = (String) body.get("email");
        return new UsernamePasswordAuthenticationToken(email, null, Set.of(new SimpleGrantedAuthority(Role.USER.getKey())));
    }

}
