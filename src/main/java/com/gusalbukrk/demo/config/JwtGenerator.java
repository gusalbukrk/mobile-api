package com.gusalbukrk.demo.config;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts.SIG;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
  // private static final KeyPair keyPair =
  // Keys.keyPairFor(SignatureAlgorithm.RS256);
  // private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
  private static final SecretKey key = SIG.HS512.key().build();

  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

    String token = Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(expireDate)
        .signWith(key)
        // .signWith(key, SignatureAlgorithm.HS512)
        .compact();
    System.out.println("New token :");
    System.out.println(token);
    return token;
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
        .verifyWith(key)
        // .setSigningKey(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();

    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token);

      return true;
    } catch (Exception ex) {
      throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect", ex.fillInStackTrace());
    }
  }
}
