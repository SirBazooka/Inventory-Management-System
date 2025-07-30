package com.example.inventorymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for testing (optional)
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/product/low-stock").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/product/**").hasRole("STAFF")
//                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/supplier/**").hasRole("STAFF")
//                        .requestMatchers(HttpMethod.POST, "/supplier/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/supplier/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/supplier/**").hasRole("ADMIN")
                                .requestMatchers("/", "/products", "/suppliers", "/css/**", "/js/**").permitAll()

                                // API endpoints require authentication
                                .requestMatchers(HttpMethod.GET, "/product/**", "/supplier/**").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers(HttpMethod.POST, "/product/**", "/supplier/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/product/**", "/supplier/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/product/**", "/supplier/**").hasRole("ADMIN")

                                .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll() // Require authentication for all requests
                )
                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic authentication
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        // Allow public access to web pages
//                        .requestMatchers("/", "/products", "/suppliers", "/css/**", "/js/**").permitAll()
//
//                        // API endpoints require authentication
//                        .requestMatchers(HttpMethod.GET, "/product/**", "/supplier/**").hasAnyRole("ADMIN", "STAFF")
//                        .requestMatchers(HttpMethod.POST, "/product/**", "/supplier/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/product/**", "/supplier/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/product/**", "/supplier/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }


    @Bean
    public UserDetailsService userDetailsService() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // only updates the stock/quantity
        // cannot delete or manage
        UserDetails staff = User.withUsername("staff")
                .password(passwordEncoder.encode("staff"))
                .roles("STAFF")
                .build();

        // full access to the data
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        // see the data
        // cannot interact with it
        UserDetails user = User.withUsername("user")
                .password("user")
//                .roles("ROLE_USER")
                .build();

        return new InMemoryUserDetailsManager(staff, admin, user);
    }
}
