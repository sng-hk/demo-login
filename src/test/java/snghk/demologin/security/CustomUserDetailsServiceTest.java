package snghk.demologin.security;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import snghk.demologin.domain.User;
import snghk.demologin.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Rollback
class CustomUserDetailsServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Test
    void 사용자_정상_조회() {
        // given
        userRepository.save(User.builder()
                .username("pingping")
                .password("secret")
                .build());

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername("pingping");

        // then
        assertThat(userDetails.getUsername()).isEqualTo("pingping");
        assertThat(userDetails.getPassword()).isEqualTo("secret");
    }

    @Test
    void 존재하지_않는_사용자_예외_발생() {
        // expect
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonexistent");
        });
    }
}

