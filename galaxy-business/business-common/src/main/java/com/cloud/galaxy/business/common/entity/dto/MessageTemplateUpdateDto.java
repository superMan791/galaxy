package com.cloud.galaxy.business.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel
public class MessageTemplateUpdateDto implements Serializable {
    /**
     * 中文标题
     */
    @Length(min = 1, max = 50, message = "字段长度在50位以下")
    @ApiModelProperty("标题")
    private String title;

    @Length(min = 1, max = 50, message = "字段长度在50位以下")
    @ApiModelProperty("模板名称")
    private String name;

    /**
     * 中文内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 说明，如参数代码
     */
    @Length(max = 500, message = "字段长度不能超过500")
    @ApiModelProperty("说明，如参数代码")
    private String description;

    @ApiModelProperty("模板类型，0短信，1 邮件")
    private Integer type;
}
