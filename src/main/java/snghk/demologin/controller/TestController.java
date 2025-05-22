package snghk.demologin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/secure")
    public ResponseEntity<String> secureEndpoint(Authentication authentication) {
        String name = authentication.getName();
        return ResponseEntity.ok("인증된 사용자 : " + name +  " 🎉");
    }
}
