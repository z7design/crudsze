package com.tr.security;

import com.tr.domain.entities.User;
import com.tr.domain.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {

  @Autowired private UserRepository userRepository;
  // Tenta encontrar o usuario pelo email e se conseguir retorna o usuário
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findByEmail(username);

    if (optionalUser.isPresent()) {
      throw new UsernameNotFoundException("Usuário ou senha inválidos");
    }

    return optionalUser.get();
  }
}
