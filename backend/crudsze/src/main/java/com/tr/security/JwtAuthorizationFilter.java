package com.tr.security;

import com.tr.domain.entities.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtUltils jwtUtil;
  private UserDetailsSecurityServer userDetailsSecurityServer;

  public JwtAuthorizationFilter(
      AuthenticationManager authenticationManager,
      JwtUltils jwtUtil,
      UserDetailsSecurityServer userDetailsSecurityServer) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsSecurityServer = userDetailsSecurityServer;
  }

  @Override
  public void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");

    // se é o token é invalido ou
    if (header != null && header.startsWith("Bearer ")) {
      UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

      if (auth != null && auth.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    // valida o token
    if (jwtUtil.isValidToken(token)) {
      String email = jwtUtil.getUserName(token);
      User user = (User) userDetailsSecurityServer.loadUserByUsername(email);
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    return null;
  }
}
