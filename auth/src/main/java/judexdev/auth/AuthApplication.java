package judexdev.auth;

import judexdev.auth.domain.User;
import judexdev.auth.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(new User("Johnny Guitar", encoder.encode("password"), "ROLE_ADMIN"));
            repo.save(new User("Fantastic", encoder.encode("password"), "ROLE_ADMIN"));
            System.out.println(repo.findAll());
        };
    }

//    @Bean
//    public ServletRegistrationBean h2servletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//        registration.addUrlMappings("/console/*");
//        return registration;
//    }
}
