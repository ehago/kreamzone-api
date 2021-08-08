package com.ehago.kreamzone.config.auth;

import com.ehago.kreamzone.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String jwtToken = getToken(req);
        try {
            Authentication authentication = JwtTokenProvider.authenticate(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(401, e.getMessage());
            res.setContentType("application/json");
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    private String getToken(HttpServletRequest req) {
        String authorizationHeader = req.getHeader("Authorization");
        if (hasBearerToken(authorizationHeader)) {
            return extractJwtToken(authorizationHeader);
        }
        return null;
    }

    private boolean hasBearerToken(String authorizationHeader) {
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ");
    }

    private String extractJwtToken(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
