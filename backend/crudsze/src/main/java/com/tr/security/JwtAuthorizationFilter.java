package com.tr.security;

import com.tr.api.responses.UserResponse;
import com.tr.domain.entities.User;
import com.tr.domain.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtUltils jwtUtil;
  @Autowired private UserService service;
  @Autowired private ModelMapper mapper;
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
      UserResponse userResponse = service.findByEmail(email);
      User user = mapper.map(userResponse, User.class);

      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    return null;
  }
}
