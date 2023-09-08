package com.tr.domain.User;

import static org.junit.jupiter.api.Assertions.*;
import com.tr.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class UserServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService service;

  @Autowired
  private JwtUtil jwtUtil;


  @Test
  void findAll() {
    }

  @Test
  void findById() {
    }

  @Test
  void save() {
    }

  @Test
  void update() {
    }

  @Test
  void delete() {
    }

  @Test
  void findByEmail() {
    }
}