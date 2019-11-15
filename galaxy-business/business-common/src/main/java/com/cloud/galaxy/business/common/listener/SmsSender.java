package com.cloud.galaxy.business.common.listener;

import com.cloud.galaxy.business.common.entity.SmsMessage;
import com.cloud.galaxy.business.common.service.IMessageTemplateService;
import com.cloud.galaxy.business.common.util.SMSUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsSender {
    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private IMessageTemplateService messageTemplateService;

    @KafkaListener(topics = "sms")
    public void smsListener(SmsMessage smsMessage) throws JsonProcessingException {

        Map<String, String> msg = messageTemplateService.createMessageByTemplate(smsMessage.getParam(), smsMessage.getTemplateCode());
        //如果返回的消息体为空或者内容为空，说明传递的参数有误，没有对应的模板或者消息模板没有内容，这时候丢弃该消息
        if (msg == null || msg.get("content") == null) {
            return;
        }
        smsUtils.sendSMSContent(smsMessage.getNationCode(), smsMessage.getPhoneNumbers(), msg.get("content"));
    }
}
