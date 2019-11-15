package com.cloud.galaxy.business.common.entity;

import lombok.Data;

import java.util.Map;

@Data
public class EmailMessageBuilder {
    private EmailMessage emailMessage = new EmailMessage();

    public EmailMessageBuilder withTitle(String title) {
        this.emailMessage.setTitle(title);
        return this;
    }

    public EmailMessageBuilder withContent(String content) {
        this.emailMessage.setContent(content);
        return this;
    }

    public EmailMessageBuilder withAttachment(Map<String, String> attachment) {
        this.emailMessage.setAttachment(attachment);
        return this;
    }

    public EmailMessageBuilder withInline(Map<String, String> inline) {
        this.emailMessage.setInline(inline);
        return this;
    }

    public EmailMessageBuilder withTo(String[] to) {
        this.emailMessage.setTo(to);
        return this;
    }

    public EmailMessage build() {
        return this.emailMessage;
    }
}
