package hft.matthew.LibraryManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/public/**", "/css/**", "/js/**").permitAll() // Allow access to login and public resources
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll() // Allow everyone to access the login page
                        .defaultSuccessUrl("/home", true) // Redirect to /home after successful login
                )
                .logout(logout -> logout
                        .permitAll() // Allow everyone to access the logout endpoint
                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails member = User.withUsername("member")
                .password("{noop}password")
                .roles("Member")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}adminpassword")
                .roles("Admin")
                .build();

        UserDetails librarian = User.withUsername("librarian")
                .password("{noop}librarianpassword")
                .roles("Librarian")
                .build();

        return new InMemoryUserDetailsManager(member, admin, librarian);
    }


}
