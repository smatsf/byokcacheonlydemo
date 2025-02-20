package com.dod.dha.byok;
import org.springframework.security.web.csrf.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
/*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }
*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	 http
            .authorizeRequests(a -> a
                .requestMatchers("/","/home","/oauth2/**", "/login/**", "/logout/**", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
         /*   .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )*/
	.logout(l -> l
            .logoutSuccessUrl("/").permitAll()
        ).csrf(c -> c
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
            .oauth2Login();
       
	return http.build();

    }

}
