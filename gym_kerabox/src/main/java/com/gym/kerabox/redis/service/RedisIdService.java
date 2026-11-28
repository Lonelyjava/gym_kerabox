package com.gym.kerabox.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisIdService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisIdService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Integer generateId(String key) {
        Long id = redisTemplate.opsForValue().increment(key);
        return id.intValue();
    }
}
