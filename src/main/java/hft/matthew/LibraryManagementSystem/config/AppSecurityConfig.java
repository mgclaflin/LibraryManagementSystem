package hft.matthew.LibraryManagementSystem.config;

import hft.matthew.LibraryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Autowired
    private UserService userService; // Ensure this implements UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // Set your custom UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Set the password encoder
        return authProvider;
    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/public/**")) // Example: Disable CSRF for specific endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/login", "/public/**").permitAll() // Public endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only endpoints
                        .requestMatchers("/librarian/**").hasRole("LIBRARIAN") // Librarian-only endpoints
                        .requestMatchers("/member/**").hasRole("MEMBER") // Member-only endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/home", true) // Redirect to dashboard after login
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Custom logout URL
                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                        .permitAll() // Allow everyone to access the logout endpoint
                )
                .authenticationProvider(authenticationProvider()); // Use your custom authentication provider

        return http.build();
    }


//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/public/**")) // Example: Disable CSRF for specific endpoints
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**").permitAll() // Public endpoints
//                        .anyRequest().authenticated() // All other endpoints require authentication
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .defaultSuccessUrl("/home", true) // Redirect to dashboard after login
//                        .permitAll() // Allow everyone to access the login page
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // Custom logout URL
//                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
//                        .permitAll() // Allow everyone to access the logout endpoint
//                );
//
//        return http.build();
//    }
}