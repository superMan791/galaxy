package com.cloud.galaxy.business.common.client.entity.dto;

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

    public EmailMessageBuilder withTo(Map<String, String> param) {
        this.emailMessageDto.setParam(param);
        return this;
    }

    public EmailMessageBuilder withTo(String templateCode) {
        this.emailMessageDto.setTemplateCode(templateCode);
        return this;
    }
}
