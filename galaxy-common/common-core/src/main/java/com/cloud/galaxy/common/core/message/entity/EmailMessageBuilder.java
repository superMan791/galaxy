package com.cloud.galaxy.common.core.message.entity;

import java.util.Map;

public class EmailMessageBuilder {
    private EmailMessageDto emailMessageDto = new EmailMessageDto();

    public EmailMessageBuilder withTo(String[] to) {
        this.emailMessageDto.setTo(to);
        return this;
    }

    public EmailMessageBuilder withInline(Map<String, String> inline) {
        this.emailMessageDto.setInline(inline);
        return this;
    }

    public EmailMessageBuilder withAttachment(Map<String, String> attachment) {
        this.emailMessageDto.setAttachment(attachment);
        return this;
    }

    public EmailMessageBuilder withParam(Map<String, String> param) {
        this.emailMessageDto.setParam(param);
        return this;
    }

    public EmailMessageBuilder withTemplateCode(String templateCode) {
        this.emailMessageDto.setTemplateCode(templateCode);
        return this;
    }

    public EmailMessageDto build() {
        return this.emailMessageDto;
    }
}
