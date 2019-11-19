package com.cloud.galaxy.business.message.listener;

import com.cloud.galaxy.business.message.service.IMessageTemplateService;
import com.cloud.galaxy.business.message.util.SMSUtil;
import com.cloud.galaxy.common.core.message.entity.SmsMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsListener {
    @Autowired
    private SMSUtil smsUtil;
    @Autowired
    private IMessageTemplateService messageTemplateService;

    //@KafkaListener(topics = "sms")
    public void smsListener(SmsMessageDto smsMessageDto) throws JsonProcessingException {
        Map<String, String> map = messageTemplateService.createMessageByTemplate(smsMessageDto.getParam(), smsMessageDto.getTemplateCode());
        smsUtil.sendSMSContent(smsMessageDto.getNationCode(), StringUtils.join(smsMessageDto.getTo(), ","), map.get("content"));
    }
}
