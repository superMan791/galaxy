package com.cloud.galaxy.common.core.message.sender;

import com.cloud.galaxy.common.core.message.entity.EmailMessageDto;
import com.cloud.galaxy.common.core.message.entity.SmsMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final String emailTopic = "email";
    private static final String smsTopic = "sms";
    @Autowired
    ObjectMapper objectMapper;

    public void sendEmail(EmailMessageDto emailMessageDto) {
        try {
            kafkaTemplate.send(emailTopic, objectMapper.writeValueAsString(emailMessageDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendSms(SmsMessageDto smsMessageDto) {
        try {
            kafkaTemplate.send(smsTopic, objectMapper.writeValueAsString(smsMessageDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
