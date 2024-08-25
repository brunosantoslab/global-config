/**
 * Security configuration for the Config Server.
 * Protects endpoints and requires authentication.
 * 
 * @author Bruno da Silva Santos <contact@brunosantos.app>
 * @since 0.0.1-alpha
 * @see <a href="https://github.com/brunosantoslab/global-config/wiki">Global Config Documentation</a>
 */
package app.brunosantos.config.configserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${BRUNOSANTOS_CONFIG_SERVER_USER}")
    private String adminUsername;

    @Value("${BRUNOSANTOS_CONFIG_SERVER_PASSWORD}")
    private String adminPassword;

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(adminUsername)
                .password(bCryptPasswordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic();  // This enables basic authentication

        return http.build();
    }
}