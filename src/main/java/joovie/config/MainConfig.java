package joovie.config;

import joovie.models.validators.UserValidator;
import joovie.repos.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MainConfig {
    private final UserRepository userRepository;

    public MainConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator(userRepository);
    }
}
