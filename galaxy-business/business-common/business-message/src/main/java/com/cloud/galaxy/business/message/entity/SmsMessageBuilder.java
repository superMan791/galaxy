package com.cloud.galaxy.business.message.entity;

import lombok.Data;

@Data
public class SmsMessageBuilder {
    private SmsMessage smsMessage = new SmsMessage();

    public SmsMessageBuilder withParam(String content) {
        this.smsMessage.setContent(content);
        return this;
    }

    public SmsMessageBuilder withNationCode(String nationCode) {
        this.smsMessage.setNationCode(nationCode);
        return this;
    }

    public SmsMessageBuilder withPhoneNumbers(String phoneNumbers) {
        this.smsMessage.setPhoneNumbers(phoneNumbers);
        return this;
    }

    public SmsMessage build() {
        return this.smsMessage;
    }
}
