package com.cloud.galaxy.common.core.message.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SmsMessageDto extends AbstractMessageDto {
    @NotBlank(message = "拨号代码不能为空")
    private String nationCode; //国际拨号代码
}
