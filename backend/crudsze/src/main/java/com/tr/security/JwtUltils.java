package com.tr.security;

import com.tr.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUltils {
  @Value("${auth.jwt.secret}")
  private String jwtSecret;

  @Value("${auth.jwt-expiration-milliseg}")
  private Long jwtExpirationMIllisegs;

  public String generationToken(Authentication authentication) {

    // Pega a data atual e soma 1 dia em millisegundos
    Date dateExpiration = new Date(new Date().getTime() + jwtExpirationMIllisegs);

    // precisa pegar o usuario atual da autenticação
    User user = (User) authentication.getPrincipal();

    try {
      // gera a chave com base na secret.
      Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));

      // gerando o token
      return Jwts.builder()
          .setSubject(user.getUsername())
          .setIssuedAt(new Date())
          .setExpiration(dateExpiration)
          .signWith(secretKey)
          .compact();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "";
    }
  }
  // Este metodo é responsável por pegar/descobrir o conteudo do token com base na chave privada
  // quais as permissões do User.
  private Claims getClaims(String token) {
    try {
      try {
        Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
        Claims claims =
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims;
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null; // caso não consiga não retorna nada
    }
  }

  // pega o email do usuario dentro do token.
  public String getUserName(String token) {
    Claims claims = getClaims(token);

    if (claims == null) {
      return null;
    }

    return claims.getSubject();
  }

  // Validação do token.
  public boolean isValidToken(String token) {
    Claims claims = getClaims(token);

    if (claims == null) {
      return false; // Retorna falso porque é um boolean
    }

    String email = claims.getSubject();
    Date dataExpiration = claims.getExpiration();
    Date now = new Date(System.currentTimeMillis());

    if (email != null && now.before(dataExpiration)) {
      return true;
    }

    return false;
  }
}
