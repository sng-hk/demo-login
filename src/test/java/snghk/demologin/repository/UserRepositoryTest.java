package snghk.demologin.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import snghk.demologin.domain.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 사용자_저장_및_조회() {
        // given
        User user = User.builder()
                .username("pingping")
                .password("encoded-password")
                .build();

        // when
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByUsername("pingping");

        // then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("pingping");
    }
}
