package com.cloud.galaxy.business.common.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

@Data
public class AbstractMessageDto implements Serializable {
    private Map<String, String> param;        //内容
    @NotBlank(message = "消息模板不能为空")
    private String templateCode;   //消息模板
}
