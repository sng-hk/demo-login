package snghk.demologin.security;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import snghk.demologin.domain.LoginHistory;
import snghk.demologin.domain.User;
import snghk.demologin.repository.LoginHistoryRepository;
import snghk.demologin.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class LoginHistoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Test
    void 로그인_시_기록이_저장된다() {
        // given
        User user = User.builder()
                .username("pingping")
                .password("encoded-secret") // 이미 인코딩된 비밀번호라고 가정
                .build();
        userRepository.save(user);

        // when: 로그인 시도
        userDetailsService.loadUserByUsername("pingping");

        // then: 로그인 기록이 저장되어야 함
        List<LoginHistory> historyList = loginHistoryRepository.findAll();
        assertThat(historyList).hasSize(1);
        assertThat(historyList.get(0).getUser().getUsername()).isEqualTo("pingping");
        assertThat(historyList.get(0).getLoginTime()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}
