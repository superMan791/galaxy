package com.cloud.galaxy.common.core.message.entity;

import lombok.Data;

import java.util.Map;

@Data
public class EmailMessageDto extends AbstractMessageDto {
    private Map<String, String> inline;  //嵌入到html内容中的图片， key为绑定的变量名，value为图片地址
    private Map<String, String> attachment; //key为附件的id,value为附件的地址
}
