package com.gusalbukrk.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gusalbukrk.demo.config.JwtGenerator;
import com.gusalbukrk.demo.dto.AuthResponseDto;
import com.gusalbukrk.demo.dto.LoginDto;
import com.gusalbukrk.demo.dto.RegisterDto;
import com.gusalbukrk.demo.model.User;
import com.gusalbukrk.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JwtGenerator jwtGenerator;

  public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
      PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtGenerator = jwtGenerator;
  }

  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
      return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
    }

    User user = new User();
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    userRepository.save(user);

    return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
  }

  @PostMapping("login")
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtGenerator.generateToken(authentication);

    return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
  }
}
