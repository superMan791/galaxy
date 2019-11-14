package com.cloud.galaxy.test.test.controller;

import com.cloud.galaxy.test.test.entity.Role;
import com.cloud.galaxy.test.test.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class TestController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("test")
    public void test() throws JsonProcessingException {
        User user1 = (User) redisTemplate.opsForValue().get("obj:9223372036854775807");
        User user2 = objectMapper.readValue(stringRedisTemplate.opsForValue().get("str:9223372036854775807"), User.class);
        log.info("user1: " + user1);
        log.info("user2: " + user2);

    }
}
