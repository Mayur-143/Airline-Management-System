package com.example.AirlineManagementSystem.config;

//import com.example.AirlineManagementSystem.service.CustomUserDetailsService;
import com.example.AirlineManagementSystem.service.UserService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**","/js/**","/images/**").permitAll()
                        .requestMatchers("/login", "/register","/home").permitAll()  // Permit access to login and register
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("primaryEmail")
		  	.passwordParameter("password")
                        .loginProcessingUrl("/login")
                        // .defaultSuccessUrl("/home", true)  // Redirect to home after successful login
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/login?error=true")  // Set the failure URL
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

   
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
    // Use userService for user details and BCryptPasswordEncoder for password encoding
    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    return authenticationManagerBuilder.build();
    }
    
    // Custom success handler to redirect based on role
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Get the role of the logged-in user
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals("ROLE_admin")) {
                    response.sendRedirect("/admin/home"); // Redirect admin
                    return;
                } else if (authority.getAuthority().equals("ROLE_user")) {
                    response.sendRedirect("/user/home"); // Redirect normal user
                    return;
                }
            }
            response.sendRedirect("user/home"); // Default redirection
        };
    }

   @Bean
   public SpringSecurityDialect springSecurityDialect() {
       return new SpringSecurityDialect();
   }

}
