package com.sh26.project.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public class CookieUtil {
  private static final String COOKIE_NAME = "schedule_user_id";
  private static final int ONE_YEAR = 60 * 60 * 24 * 365;

  // Get existing user ID or create a new one
  public static String getOrCreateUserId(HttpServletRequest request, HttpServletResponse response) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (COOKIE_NAME.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    // No cookie found — create a new user ID
    String newId = UUID.randomUUID().toString();
    Cookie cookie = new Cookie(COOKIE_NAME, newId);
    cookie.setMaxAge(ONE_YEAR);
    cookie.setPath("/");
    cookie.setHttpOnly(true); // not accessible from JavaScript (safer)
    response.addCookie(cookie);
    return newId;
  }
}
