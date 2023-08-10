package com.tr.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tr.common.DateConveter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tr.domain.User.LoginRequestDTO;
import com.tr.domain.User.LoginResponse;
import com.tr.domain.User.UserEntity;
import com.tr.domain.User.UserResponse;
import com.tr.domain.exception.ErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        try {
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
            Authentication auth  = authenticationManager.authenticate(authToken);
            return auth;

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Username or password is invalid");

        } catch(Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
    FilterChain chain, Authentication authResult) throws IOException{
        
        UserEntity user  = (UserEntity) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);
        
        UserResponse userResponse =  new UserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setFoto(user.getFoto());
        userResponse.setDateInativation(user.getDateInativation());
        userResponse.setDateRegistration(user.getDateRegistration());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("Bearer " + token);
        loginResponse.setUser(userResponse);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
        AuthenticationException failed)  throws IOException, ServletException {

            String dateTime = DateConveter.convertDateToDateETime(new Date());

            ErrorResponse erro = new ErrorResponse(dateTime, 401, "Unauthorized", failed.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    
            response.setCharacterEncoding("UTF-8"); 
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(erro));
    }

}
