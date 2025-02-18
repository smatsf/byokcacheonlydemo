package com.dod.dha.byok.config;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.dod.dha.byok.*;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor requestInterceptor;
//    @Autowired
//    private NonceInterceptor nonceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
  //      registry.addInterceptor(nonceInterceptor).addPathPatterns("/**"); // Apply to all endpoints
    }

}

