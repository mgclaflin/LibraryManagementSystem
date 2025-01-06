package hft.matthew.LibraryManagementSystem.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "hft.matthew.LibraryManagementSystem.repository")
public class RepositoryConfig {
    // Other configurations
}