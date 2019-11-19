package com.cloud.galaxy.business.message.controller;

import com.cloud.galaxy.business.message.entity.EmailMessage;
import com.cloud.galaxy.business.message.entity.SmsMessage;
import com.cloud.galaxy.common.core.base.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@RequestMapping("msc")
@RestController
public class MscController {
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${sms.hour_max_send}")
    private Integer smsHourMaxSend;
    @Value("${sms.day_max_send}")
    private Integer smsDayMaxSend;
    private static final String emailTopic = "email";
    private static final String smsTopic = "sms";

    @PostMapping("sendEmail")
    public R sendEmail(@RequestBody @Validated EmailMessage emailMessage) {
        kafkaTemplate.send(emailTopic, emailMessage);
        return R.ok();
    }

    @PostMapping("sendSms")
    public R sendSms(@Validated @RequestBody SmsMessage smsMessage) {
        for (String phone : smsMessage.getPhoneNumbers().split(",")) {
            Integer x = (Integer) redisTemplate.opsForValue().get("sms_" + phone + "_" + LocalTime.now().getHour());
            Integer hourCount = x != null ? x : 0;
            Integer y = (Integer) redisTemplate.opsForValue().get("sms_" + phone + "_" + LocalDate.now());
            Integer dayCount = y != null ? y : 0;
            //如果当天或者1个小时内发送的短信数超过上限，就拒绝发送
            if (hourCount >= smsHourMaxSend || dayCount >= smsDayMaxSend) {
                return R.fail();
            }
            //设置一小时过期时间
            redisTemplate.opsForValue().set("sms_" + phone + "_" + LocalTime.now().getHour(), ++hourCount, 1, TimeUnit.HOURS);
            redisTemplate.opsForValue().set("sms_" + phone + "_" + LocalDate.now(), ++dayCount, 1, TimeUnit.DAYS);
        }
        kafkaTemplate.send(smsTopic, smsMessage);
        return R.ok();
    }
}
