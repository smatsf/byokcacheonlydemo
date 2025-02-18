package com.dod.dha.byok.config;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
 private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.debug("Request to endpoint: " + httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
    }
}


