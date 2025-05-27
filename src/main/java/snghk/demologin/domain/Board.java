package snghk.demologin.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 연관 관계 주인
    @JoinColumn(name = "user_id") // FK column name을 user_id로 명시
    private User user;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
