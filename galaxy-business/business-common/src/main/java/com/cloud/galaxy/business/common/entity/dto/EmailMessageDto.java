package com.cloud.galaxy.business.common.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
public class EmailMessageDto extends AbstractMessageDto {
    @NotNull(message = "接收人不能为空")
    @Size(min = 1, message = "接收人不能少于一个")
    private String[] to;        //接收人
    private Map<String, String> inline;  //嵌入到html内容中的图片， key为绑定的变量名，value为图片地址
    private Map<String, String> attachment; //key为附件的id,value为附件的地址
}
