package com.cloud.galaxy.business.common.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class SmsMessageDto {
    @NotBlank(message = "拨号代码不能为空")
    private String nationCode; //国际拨号代码
    private Map<String, String> param; //短信内容
    @NotBlank(message = "手机号不能为空")
    private String phoneNumbers;    //接收人的手机号,多个用,分割
    @NotBlank(message = "模板不能为空")
    private String templateCode;   //短信模板
    private String language; //语言
}
