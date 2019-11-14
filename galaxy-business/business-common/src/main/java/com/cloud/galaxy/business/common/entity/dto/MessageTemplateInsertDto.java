package com.cloud.galaxy.business.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel
public class MessageTemplateInsertDto implements Serializable {

    @ApiModelProperty("模板编码")
    @NotBlank(message = "code不能为空")
    @Length(min=1,max = 20,message = "code长度在1~20位之间")
    private String code;

    @Length(min = 1,max = 50,message = "字段长度在50位以下")
    @ApiModelProperty("邮件标题")
    private String title;

    @NotBlank
    @Length(min = 1,max = 50,message = "字段长度在50位以下")
    @ApiModelProperty("模板名称")
    private String name;

    @NotBlank(message = "字段不能为空")
    @ApiModelProperty("中文内容")
    private String content;

    @Length(max = 500,message = "字段长度不能超过500")
    @ApiModelProperty("说明，如参数代码")
    private String description;

    @ApiModelProperty("模板类型，0短信，1 邮件")
    private Integer type;
}
