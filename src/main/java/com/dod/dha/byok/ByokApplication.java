package com.dod.dha.byok;
import org.springframework.http.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import  org.springframework.security.oauth2.core.user.*;
import java.util.*;
@SpringBootApplication(scanBasePackages = "com.dod.dha.byok")
@RestController
public class ByokApplication {

	   @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
	public static void main(String[] args) {
	System.setProperty("spring.profiles.active", "errorhandling");
		SpringApplication.run(ByokApplication.class, args);
	}

}
