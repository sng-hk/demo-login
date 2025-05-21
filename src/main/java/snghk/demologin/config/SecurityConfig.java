package snghk.demologin.config;

import lombok.experimental.Delegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder  passwordEncoder() {
        // delegate
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
