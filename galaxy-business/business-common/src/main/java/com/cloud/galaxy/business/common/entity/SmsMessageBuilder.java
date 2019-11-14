package com.cloud.galaxy.business.common.entity;

import lombok.Data;

import java.util.Map;

@Data
public class SmsMessageBuilder {
    private SmsMessage smsMessage = new SmsMessage();

    public SmsMessageBuilder withParam(Map<String, String> param) {
        this.smsMessage.setParam(param);
        return this;
    }

    public SmsMessageBuilder withNationCode(String nationCode) {
        this.smsMessage.setNationCode(nationCode);
        return this;
    }

    public SmsMessageBuilder withTemplateCode(String templateCode) {
        this.smsMessage.setTemplateCode(templateCode);
        return this;
    }

    public SmsMessageBuilder withPhoneNumbers(String phoneNumbers) {
        this.smsMessage.setPhoneNumbers(phoneNumbers);
        return this;
    }

    public SmsMessageBuilder withLanguage(String language) {
        this.smsMessage.setLanguage(language);
        return this;
    }

    public SmsMessage build() {
        return this.smsMessage;
    }
}
