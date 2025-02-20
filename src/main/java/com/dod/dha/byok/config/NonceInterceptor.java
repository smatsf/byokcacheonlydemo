package com.dod.dha.byok.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NonceInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheManager cacheManager;

    private static final String NONCE_CACHE = "nonces";

/*    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Retrieve the nonce from the request header
        String nonce = request.getHeader("X-Nonce");

        if (nonce == null || nonce.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nonce is required");
        }

        // Check if the nonce has already been used
        if (cacheManager.getCache(NONCE_CACHE).get(nonce) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Replay attack detected");
        }

        // If nonce is not used, store it with an expiration time (e.g., 5 minutes)
        cacheManager.getCache(NONCE_CACHE).put(nonce, true);
        return true;
    }
*/
}

