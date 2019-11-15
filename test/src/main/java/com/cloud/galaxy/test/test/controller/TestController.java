//package com.cloud.galaxy.test.test.controller;
//
//import com.cloud.galaxy.common.core.base.R;
//import com.cloud.galaxy.test.test.entity.Role;
//import com.cloud.galaxy.test.test.entity.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Log4j2
//@RestController
//public class TestController {
//    @Autowired
//    ObjectMapper objectMapper;
//    @Autowired
//    RedisTemplate redisTemplate;
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//
//    @GetMapping("test1")
//    public void test1(){
//        User user=new User();
//        user.setId(Long.MAX_VALUE);
//        user.setName("tom");
//        user.setCreateTime(LocalDateTime.now());
//        user.setBirth(LocalDate.of(1992,9,13));
//        user.setBalance(new BigDecimal("12323.233243242342343"));
//        user.setAge(29);
//        user.setTime(LocalTime.now());
//        user.setRoleList(Arrays.asList(new Role(Long.MAX_VALUE,"system",new BigDecimal("234234.2342342"))));
//        redisTemplate.opsForValue().set("obj: "+user.getId(),user);
//    }
//
//    @GetMapping("test2")
//    public void test2() throws JsonProcessingException {
//        User user1 = (User) redisTemplate.opsForValue().get("obj: 9223372036854775807");
//        User user2 = objectMapper.readValue(stringRedisTemplate.opsForValue().get("str:9223372036854775807"), User.class);
//        log.info("user1: " + objectMapper.writeValueAsString(user1));
//        log.info("user2: " + objectMapper.writeValueAsString(user2));
//
//    }
//
//    @GetMapping("test3")
//    public void test3(){
//    log.info(redisTemplate.opsForValue().get("count1"));
//    log.info(stringRedisTemplate.opsForValue().get("count2"));
//    }
//
//    @PostMapping("test4")
//    public R  test4(@RequestBody @Validated User user){
//        return R.ok("成功！");
//    }
//}
