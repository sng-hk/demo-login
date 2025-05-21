package snghk.demologin.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(); // 비밀키를 내부에서 설정했다면 이렇게 인스턴스 생성
    }

    @Test
    void 토큰_생성_및_검증() {
        // given
        String username = "pingping";

        // when
        String token = jwtUtil.generateToken(username);

        // then
        assertThat(jwtUtil.validateToken(token)).isTrue();
        assertThat(jwtUtil.extractUsername(token)).isEqualTo(username);
    }

    @Test
    void 잘못된_토큰_검증실패() {
        String fakeToken = "fake.jwt.token";
        assertThat(jwtUtil.validateToken(fakeToken)).isFalse();
    }
}
