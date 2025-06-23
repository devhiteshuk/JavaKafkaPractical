package com.dhl.virtusa.redish_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedishCacheServiceImp implements RedishCacheService{
    private final RedisTemplate<String, Object> redisTemplate;

    // In any @Service or @Component
    @Autowired
    public RedishCacheServiceImp(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long countKeys() {
        int size = redisTemplate.keys("*").size();
        System.out.println("Total keys in Redis: " + size);
        return size;
    }
}
