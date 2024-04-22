package com.nicksy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
                        // CSS should be accessible for all
                        .requestMatchers("/css/**", "/webjars/**")
                        .permitAll()
                        // all other requests should be authenticated
                        .anyRequest().authenticated())
                // formLogin() is used to indicate an HTML form is going to
                // be used to present a username and password. it also adds
                // UsernamePasswordAuthenticationFilter to the filter chain
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        // this bean creates an in-memory repository of users
        // add a user to the cache:
        // username "admin" password "hi" and a ROLE_USER
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("hi"))
                        .roles("USER").build());
    }
}
