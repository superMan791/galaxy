package com.cloud.galaxy.business.message.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SmsMessage extends AbstractMessage implements Serializable {
    private String nationCode; //国际拨号代码
    private String content; //短信内容
    private String phoneNumbers;    //接收人的手机号,多个用,分割
}
