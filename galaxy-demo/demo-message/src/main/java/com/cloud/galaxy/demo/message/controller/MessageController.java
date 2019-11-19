package com.cloud.galaxy.demo.message.controller;

import com.cloud.galaxy.common.core.message.entity.EmailMessageBuilder;
import com.cloud.galaxy.common.core.message.entity.SmsMessageBuilder;
import com.cloud.galaxy.common.core.message.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageSender messageSender;

    @GetMapping("sendSms")
    public void sendSms() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", "18");
        messageSender.sendSms(new SmsMessageBuilder().withTo(new String[]{"13438902947"}).withParam(map).withTemplateCode("1").build());
    }

    @GetMapping("sendEmail")
    public void sendEmail() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", "18");
        messageSender.sendEmail(new EmailMessageBuilder().withTo(new String[]{"1439097053@qq.com"}).withParam(map).withTemplateCode("2").build());
    }
}
