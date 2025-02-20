package com.dod.dha.byok;

import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.core.user.*;
import java.util.*;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = "com.dod.dha.byok")
@RestController
public class ByokApplication {
    private static final Logger logger = LoggerFactory.getLogger(ByokApplication.class);

    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    public static void main(String[] args) {
        logger.debug("Starting BYOK");
        System.setProperty("spring.profiles.active", "errorhandling");
        SpringApplication.run(ByokApplication.class, args);
    }

}
