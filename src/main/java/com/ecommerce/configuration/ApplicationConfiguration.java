package com.ecommerce.configuration;


import com.ecommerce.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/* we will create all class and object whihc we need again and again we can use this configuration bean in any class by dependency injection
  * we can use class object without configure here but we have to create all object again and again
  * so we can use this configuration bean to create object once and use it anywhere in the project
  * */
@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    // Constructor to initialize the UserRepository dependency
    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create object of rest template and use it anywhere in the project by dependency injection
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }



    // Bean to provide UserDetailsService implementation
    @Bean
    UserDetailsService userDetailsService() {
        // Lambda function to find a user by email and throw an exception if not found
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Bean to provide BCryptPasswordEncoder for password encoding
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean to provide AuthenticationManager using the provided AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean to provide AuthenticationProvider implementation
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Set the UserDetailsService and PasswordEncoder for the authentication provider
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
