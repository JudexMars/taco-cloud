package judexdev.web.security;

import judexdev.web.domain.User;
import judexdev.web.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/design", "/orders").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                .requestMatchers(HttpMethod.DELETE, "api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                .requestMatchers(HttpMethod.POST, "api/orders").hasAuthority("SCOPE_createOrders")
                .requestMatchers(HttpMethod.DELETE, "api/orders").hasAuthority("SCOPE_deleteOrders")
                .requestMatchers(HttpMethod.PATCH, "api/orders").hasAuthority("SCOPE_editOrders")
                .requestMatchers(HttpMethod.POST, "api/tacos").hasAuthority("SCOPE_createTacos")
                .requestMatchers(HttpMethod.DELETE, "api/tacos").hasAuthority("SCOPE_deleteTacos")
                .requestMatchers(HttpMethod.PATCH, "api/tacos").hasAuthority("SCOPE_editTacos")
                .requestMatchers("/", "/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .and()
                .logout()
                .and()
                .csrf()
                .ignoringRequestMatchers("/api/**")
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }
}
