package com.cloud.galaxy.business.common.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel
@Data
public class GetMessageTemplateVo implements Serializable {

    private Long id;

    @ApiModelProperty("引用代码")
    private String code;

    @ApiModelProperty("中文标题")
    private String title;

    @ApiModelProperty("模板名称")
    private String name;

    @ApiModelProperty("中文内容")
    private String content;

    @ApiModelProperty("说明，如参数代码")
    private String description;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("模板类型，0短信，1 邮件")
    private Integer type;
}
