package com.tr.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tr.api.responses.LoginResponse;
import com.tr.api.responses.UserResponse;
import com.tr.common.ConversorDate;
import com.tr.domain.dto.LoginRequestDTO;
import com.tr.domain.entities.User;
import com.tr.domain.exception.ErrorResponse;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;
  private JwtUltils jwtUtils;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUltils jwtUtils) {
    super();
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;

    setFilterProcessesUrl("/api/auth");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {

    // Esse metodo que verifica toda a atenticação e se existe o usuario existe e se é possível
    // gerar o token
    try {
      LoginRequestDTO login =
          new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
      Authentication auth = authenticationManager.authenticate(authToken);
      return auth;

      // verifica se o email e senha são válidos
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Usuário ou senha inválidos");

      // erro interno na aplicação
    } catch (Exception e) {
      throw new InternalAuthenticationServiceException(e.getMessage());
    }
  }

  // Se a autenticação deu certo vem direto aqui...
  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException {

    // Pego os dados do user e depois gera o token
    User user = (User) authResult.getPrincipal();
    String token = jwtUtils.generationToken(authResult);

    UserResponse userResponse = new UserResponse();
    userResponse.setUserId(user.getUserId());
    userResponse.setEmail(user.getEmail());
    userResponse.setName(user.getName());
    userResponse.setFoto(user.getFoto());
    userResponse.setDateInativation(user.getDateInativation());
    userResponse.setDateRestiration(user.getDateRegistration());

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken("Bearer " + token); // Padrão Bearer
    loginResponse.setUserResponse(userResponse);

    //
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response
        .getWriter()
        .write(new Gson().toJson(loginResponse)); // Escreve a mensagem e depois transforma em json
  }
@Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
      throws IOException, SecurityException {

    // Data e hora que o erro acontenceu e depois converte
    String dateTime = ConversorDate.convertionTheDateToDateAndTime(new Date());

    // Se der qualquer erro de autenticação vai cair aqui, dai pego a data e hora e a mensagem
    ErrorResponse erro = new ErrorResponse(dateTime, 401, "Unauthorized", failed.getMessage());

    // Com o response eu seto o status
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().print(new Gson().toJson(erro));
  }
}
