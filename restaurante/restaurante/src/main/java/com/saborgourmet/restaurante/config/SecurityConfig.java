package com.saborgourmet.restaurante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desactivar CSRF correctamente en Spring Security 6
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permite todas las rutas sin login
                );
        return http.build();
    }
}
