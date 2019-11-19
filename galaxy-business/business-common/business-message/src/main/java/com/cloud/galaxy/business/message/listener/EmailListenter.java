package com.cloud.galaxy.business.message.listener;

import com.cloud.galaxy.business.message.entity.EmailMessage;
import com.cloud.galaxy.business.message.service.IMessageTemplateService;
import com.cloud.galaxy.business.message.util.SendEmailUtil;
import com.cloud.galaxy.common.core.message.entity.EmailMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Map;

@Log4j2
@Component
public class EmailListenter {
    @Autowired
    SendEmailUtil sendEmailUtil;
    @Autowired
    private IMessageTemplateService messageTemplateService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(id="emailListener",topics = "email")
    public void emailListener(String message) throws MessagingException, JsonProcessingException {
        EmailMessageDto emailMessageDto=objectMapper.readValue(message,EmailMessageDto.class);
        //如果接收邮件的人是空的，直接返回，不发了
        if (emailMessageDto.getTo() != null && emailMessageDto.getTo().length == 0) {
            return;
        }
        Map<String, String> map = messageTemplateService.createMessageByTemplate(emailMessageDto.getParam(), emailMessageDto.getTemplateCode());
        EmailMessage emailMessage = new EmailMessage();
        BeanUtils.copyProperties(emailMessageDto, emailMessage);
        emailMessage.setTitle(map.get("title"));
        emailMessage.setContent(map.get("content"));
        sendEmailUtil.sendMimeMessage(emailMessage);
    }
}
