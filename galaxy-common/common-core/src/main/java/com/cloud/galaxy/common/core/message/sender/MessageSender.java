package com.cloud.galaxy.common.core.message.sender;

import com.cloud.galaxy.common.core.message.entity.EmailMessageDto;
import com.cloud.galaxy.common.core.message.entity.SmsMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final String emailTopic = "galaxy:email";
    private static final String smsTopic = "galaxy:sms";

    public void sendEmail(EmailMessageDto emailMessageDto) {
        kafkaTemplate.send(emailTopic, emailMessageDto);
    }

    public void sendSms(SmsMessageDto smsMessageDto) {
        kafkaTemplate.send(smsTopic, smsMessageDto);
    }
}
