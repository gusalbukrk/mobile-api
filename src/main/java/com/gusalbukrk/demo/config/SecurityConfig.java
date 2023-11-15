package com.gusalbukrk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  // SecurityFilterChain bean defines which URL paths should be secured and which should not
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/api").permitAll() 
          .anyRequest().authenticated()
      )
      .formLogin(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults());

    return http.build();
  }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = org.springframework.security.core.userdetails.User
      // .withDefaultPasswordEncoder() // deprecated; used instead of .builder() and passwordEncoder()
      .builder()
      .username("admin")
      .password("$2a$12$VW9FqoEQJZCIpSM0yphSseWRq9fhdbuLNCU/pqbDJbzq60nUrL8Qu") // admin
      .build();

		return new InMemoryUserDetailsManager(user);
	}

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
