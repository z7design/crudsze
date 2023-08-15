package com.tr.domain.User;

import com.tr.domain.ICrudsServices;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ICrudsServices<UserRequestDTO, UserResponse> {

  @Autowired private UserRepository userRepository;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired private ModelMapper mapper;

  @Override
  public List<UserResponse> findAll() {
    List<UserEntity> users = userRepository.findAll();
    return users.stream()
        .map(user -> mapper.map(user, UserResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserResponse findById(Long userId) {
    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    if (optionalUser == null) {
      // se o usuario existe
      throw new ResourceNotFoundException("Unable to find user with id: " + userId);
    }
    return mapper.map(optionalUser.get(), UserResponse.class);
  }

  @Override
  public UserResponse save(UserRequestDTO dto) {

    userValidate(dto);

   Optional<UserEntity> optionalUser = userRepository.findByEmail(dto.getEmail());

    if (optionalUser == null) {
      //Se já existe um usuário cadastro com o e-mail
      throw new ResourceBadRequestException(
          "There is already a registered user with the email: " + dto.getEmail());
    }

    // Criptografando a senha do usuáiro...pego a senha publica criptografo e depois... salvo
    UserEntity userEntity = mapper.map(dto, UserEntity.class);

    // Criptografando a senha do usuáiro...pego a senha publica criptografo e depois...
    String password = bCryptPasswordEncoder.encode(userEntity.getPassword());
    // eu salvo
    userEntity.setPassword(password);

    userEntity.setUserId(null);
    userEntity.setDateRegistration(new Date());
    userEntity = userRepository.save(userEntity);

    return mapper.map(userEntity, UserResponse.class);
  }

  @Override
  public UserResponse update(Long userId, UserRequestDTO dto) {

    UserResponse userResponsebd = findById(userId);
    userValidate(dto);

    UserEntity userEntity = mapper.map(dto, UserEntity.class);

    // Criptografando a senha do usuáiro...pego a senha publica criptografo e depois... salvo
    String password = bCryptPasswordEncoder.encode(dto.getPassword());
    userEntity.setPassword(password);

    userEntity.setUserId(userId);
    userEntity.setDateInativation(userResponsebd.getDateInativation());
    userEntity.setDateRegistration(userResponsebd.getDateRegistration());
    userEntity = userRepository.save(userEntity);

    return mapper.map(userEntity, UserResponse.class);
  }

  @Override
  public void delete(Long userId) {

    Optional<UserEntity> optionalUser = userRepository.findById(userId);
    if (optionalUser == null) {
      throw new ResourceNotFoundException("Unable to find user with id: " + userId);
    }
    UserEntity userEntity = optionalUser.get();
    userEntity.setDateInativation(new Date());
    userRepository.save(userEntity);
  }
//Validação do email e senha, ou seja, são obirgatorios..
  private void userValidate(UserRequestDTO dto) {
    if (dto.getEmail() == null || dto.getPassword() == null) {
      throw new ResourceBadRequestException("Email and password are required");
    }
  }
//Busca de usuario por email
  public UserResponse findByEmail(String email) {
    Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isPresent()) {
      throw new ResourceNotFoundException("Unable to find user with email: " + email);
    }
    return mapper.map(optionalUser.get(), UserResponse.class);
  }
}
