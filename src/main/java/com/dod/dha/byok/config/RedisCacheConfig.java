package com.dod.dha.byok.config;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

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
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    return RedisCacheManager.create(connectionFactory);
  }
}

