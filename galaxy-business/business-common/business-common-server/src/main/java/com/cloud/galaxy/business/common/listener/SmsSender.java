package com.cloud.galaxy.business.common.listener;

import com.cloud.galaxy.business.common.entity.dto.SmsMessageDto;
import com.cloud.galaxy.business.common.service.IMessageTemplateService;
import com.cloud.galaxy.business.common.util.SMSUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsSender {
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
