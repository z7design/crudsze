package com.tr.domain.services;

import com.tr.api.responses.UserResponse;
import com.tr.domain.dto.UserRequestDTO;
import com.tr.domain.entities.User;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ICrudsServices<UserRequestDTO, UserResponse> {

  @Autowired private UserRepository userRepository;

  @Autowired private ModelMapper mapper;

  @Override
  public List<UserResponse> findAll() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(user -> mapper.map(user, UserResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse findById(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      // Não esquece de colocar a exception...karai
      throw new ResourceNotFoundException("Unable to find user with id: " + userId);
    }
    return mapper.map(optionalUser.get(), UserResponse.class);
  }

  @Override
  public UserResponse save(UserRequestDTO dto) {

    validateUser(dto);
    // criptografar a senha...
    User user = mapper.map(dto, User.class);
    user.setUserId(null);
    user = userRepository.save(user);

    return mapper.map(user, UserResponse.class);
  }

  @Override
  public UserResponse update(Long userId, UserRequestDTO dto) {

    UserResponse userResponsebd = findById(userId);
    validateUser(dto);

    User user = mapper.map(dto, User.class);
    // criptografar a senha...

    user.setUserId(userId);
    user.setDateInativation(userResponsebd.getDateInativation());
    user = userRepository.save(user);

    return mapper.map(user, UserResponse.class);
  }

  @Override
  public void delete(Long userId) {

    UserResponse userFound = findById(userId);
    User user1 = mapper.map(userFound, User.class);

    user1.setDateInativation(new Date());
    userRepository.save(user1);
  }

  private void validateUser(UserRequestDTO dto) {
    if (dto.getEmail() == null || dto.getPassword() == null) {
      throw new ResourceBadRequestException("Email and password are required");
    }
  }

  public UserResponse findByEmail(String email) {
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isPresent()) {
      throw new ResourceNotFoundException("Unable to find user with email: " + email);
    }
    return mapper.map(optionalUser.get(), UserResponse.class);
  }
}
