package com.ehago.kreamzone.config.auth;

import com.ehago.kreamzone.enumeration.Role;
import com.ehago.kreamzone.util.Cookies;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.ehago.kreamzone.config.auth.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.ehago.kreamzone.config.auth.JwtTokenProvider.*;

@Component
public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication authentication) throws IOException {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String accessToken = JwtTokenProvider.createAccessToken(name, email, Role.USER);
        String refreshToken = JwtTokenProvider.createRefreshToken(name, email, Role.USER);
        String redirectUri = determineTargetUrl(req, res, authentication);
        Cookies.addCookie(res, ACCESS_TOKEN_NAME, accessToken, ACCESS_TOKEN_EXPIRATION_SECONDS);
        Cookies.addCookie(res, REFRESH_TOKEN_NAME, refreshToken, REFRESH_TOKEN_EXPIRATION_SECONDS);
        this.getRedirectStrategy().sendRedirect(req, res, redirectUri);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        Cookie cookie = Cookies.getCookie(req, REDIRECT_URI_PARAM_COOKIE_NAME);
        if (cookie == null) {
            return getDefaultTargetUrl();
        }
        return cookie.getValue();
    }

}