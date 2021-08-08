package com.ehago.kreamzone.util;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

public final class Cookies {

    private Cookies() {
    }

    public static Cookie getCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (isEmpty(cookies)) {
            return null;
        }
        return find(cookies, cookieName);
    }

    public static boolean isEmpty(Cookie[] cookies) {
        return cookies == null || cookies.length == 0;
    }

    public static Cookie find(Cookie[] cookies, String cookieName) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse res, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        res.addCookie(cookie);
    }

    public static void removeCookie(HttpServletRequest req, HttpServletResponse res, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (isEmpty(cookies)) {
            return;
        }
        findAndRemove(res, cookies, cookieName);
    }

    private static void findAndRemove(HttpServletResponse res, Cookie[] cookies, String cookieName) {
        Cookie cookie = find(cookies, cookieName);
        if (cookie == null) {
            return;
        }
        cookie.setValue("");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> clazz) {
        return clazz.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue())));
    }

}
