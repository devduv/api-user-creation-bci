package com.bci.pruebatecnica.utils;

import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtil {

  /**
   * Generate jwt.
   *
   * @param email email
   * @return jwt
   */
  public static String generateToken(String email) {
    return Jwts.builder()
        .subject(email)
        .compact();
  }
}
