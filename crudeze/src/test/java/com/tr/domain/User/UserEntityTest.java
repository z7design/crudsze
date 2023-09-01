package com.tr.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class UserEntityTest {
  @Mock
  private UserRepository repository;
  @InjectMocks
  private UserService service;
  private UserEntity userEntity;

  @BeforeEach
  public void setUp() {

    userEntity = new UserEntity();
  }

  @Test
  void shouldNewUserWhenSave() {

  }
}
