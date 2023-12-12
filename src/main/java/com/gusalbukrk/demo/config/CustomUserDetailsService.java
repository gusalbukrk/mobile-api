package com.gusalbukrk.demo.config;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gusalbukrk.demo.model.User;
import com.gusalbukrk.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        Collections.emptyList());
  };
}