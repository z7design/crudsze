package com.tr.security;

import java.util.Optional;

import com.tr.domain.User.UserEntity;
import com.tr.domain.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsSecurityServer implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isPresent()){
            throw new UsernameNotFoundException("Username or password is invalid");
        }

        return optionalUser.get();
    }
    
}
