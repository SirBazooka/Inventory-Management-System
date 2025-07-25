package com.example.inventorymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (optional)
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll() // Require authentication for all requests
                )
                .httpBasic(); // Enable HTTP Basic authentication
        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails staff = User.withUsername("staff")
                .password("ROLE_STAFF")
//                .roles("ROLE_USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("ROLE_ADMIN")
//                .roles("ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(staff, admin);
    }
}
