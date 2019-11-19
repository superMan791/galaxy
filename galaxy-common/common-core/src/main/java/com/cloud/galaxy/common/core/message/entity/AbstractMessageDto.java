package com.cloud.galaxy.common.core.message.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Map;

@Data
public class AbstractMessageDto implements Serializable {
    private Map<String, String> param;        //内容
    @NotBlank(message = "消息模板不能为空")
    private String templateCode;   //消息模板
    @NotNull(message = "接收人不能为空")
    @Size(min = 1, message = "接收人不能少于一个")
    private String[] to;        //接收人
}
