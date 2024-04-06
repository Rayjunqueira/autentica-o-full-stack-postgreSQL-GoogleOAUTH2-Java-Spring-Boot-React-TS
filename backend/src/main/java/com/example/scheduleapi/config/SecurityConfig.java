package com.example.scheduleapi.config;
import com.example.scheduleapi.security.FilterToken;
import com.example.scheduleapi.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public FilterToken filterTokenJwt() {
        return new FilterToken();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/user").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/user/check-email").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/login/oauth2/code/google").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .redirectionEndpoint(redirectionEndpoint ->
                                        redirectionEndpoint
                                                .baseUri("/login/oauth2/code/google")
                                )
                )
                .userDetailsService(userDetailsService);
        http.addFilterBefore(filterTokenJwt(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
