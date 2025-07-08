package com.example.employee_management_system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/employees/**").hasRole("ADMIN")
            .requestMatchers("/api/departments/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults()); // 👈 modern way

    return http.build();
}

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
            User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build()
        );
        manager.createUser(
            User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build()
        );
        return manager;
    }

    // Optional: only needed if you're injecting AuthenticationManager somewhere
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
