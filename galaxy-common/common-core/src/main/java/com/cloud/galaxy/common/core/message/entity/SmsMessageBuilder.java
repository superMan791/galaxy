package com.cloud.galaxy.common.core.message.entity;

import java.util.Map;

public class SmsMessageBuilder {
    private SmsMessageDto smsMessageDto = new SmsMessageDto();

    public SmsMessageBuilder withNationCode(String nationCode) {
        this.smsMessageDto.setNationCode(nationCode);
        return this;
    }

    public SmsMessageBuilder withParam(Map<String, String> param) {
        smsMessageDto.setParam(param);
        return this;
    }

    public SmsMessageBuilder withTemplateCode(String templateCode) {
        smsMessageDto.setTemplateCode(templateCode);
        return this;
    }

    public SmsMessageBuilder withTo(String[] to) {
        smsMessageDto.setTo(to);
        return this;
    }

    public SmsMessageDto build() {
        return this.smsMessageDto;
    }
}
