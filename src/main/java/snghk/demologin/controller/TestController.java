package snghk.demologin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import snghk.demologin.domain.Board;
import snghk.demologin.domain.User;
import snghk.demologin.dto.board.BoardSaveRequest;
import snghk.demologin.repository.BoardRepository;
import snghk.demologin.repository.UserRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @GetMapping("/secure")
    public ResponseEntity<String> secureEndpoint(Authentication authentication) {
        String name = authentication.getName();
        return ResponseEntity.ok("인증된 사용자 : " + name + " 🎉");
    }

    @PostMapping("/post")
    public ResponseEntity<String> post(Authentication authentication, @RequestBody BoardSaveRequest request) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
        boardRepository.save(Board.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build());

        return ResponseEntity.status(201).body("Created");
    }
}
