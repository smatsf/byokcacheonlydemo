package com.dod.dha.byok.config;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCacheConfig {

    /*    @Bean
        public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
            RedisCacheManager cacheManager = RedisCacheManager.create(redisTemplate);
            
            // Set TTL for nonces (e.g., 5 minutes)
            cacheManager.setCacheNames(List.of("nonces"));
            cacheManager.getCache("nonces").getNativeCache().expire(300, TimeUnit.SECONDS);  // TTL of 5 minutes
            return cacheManager;
        }*/
    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    return RedisCacheManager.create(connectionFactory);
  }
}

