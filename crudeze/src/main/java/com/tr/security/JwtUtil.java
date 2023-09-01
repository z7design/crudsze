package com.tr.security;

import com.tr.domain.User.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    
    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt-expiration-milliseg}")
    private Long jwtExpirationMilliseg;

    public String gerarToken(Authentication authentication){

        // Ele pega a data atual e soma mais 1 dia em milliseconds
        Date dataExpiracao = new Date(new Date().getTime() + jwtExpirationMilliseg);

        // Aqui pegamos o usuário atual da autenticação.
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        try {
            
            // Aqui gero uma chave com base na secret.
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            
            // Aqui que gera o token.
            return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(secretKey)
                .compact();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

    }


    // Metodo que pra descobrir de dentro do token com base na chave privada qual as permissões do usuário.
    private Claims getClaims(String token){

        try {
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }


    // Metodo que sabe pegar o email do usuario dentro do token.
    public String getUserName(String token){
        Claims claims = getClaims(token);

        if(claims == null) {
            return null;
        }

        return claims.getSubject();
    }


    // Metodo para validar o token.
    public boolean isValidToken(String token){
        Claims claims = getClaims(token);

        if(claims == null) {
            return false;
        }

        String email = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());

        if(email != null && now.before(expirationDate)){
            return true;
        }

        return false;
    }

}
