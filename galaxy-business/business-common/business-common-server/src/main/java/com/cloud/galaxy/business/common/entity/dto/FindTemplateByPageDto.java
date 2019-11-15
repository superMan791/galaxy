package com.cloud.galaxy.business.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class FindTemplateByPageDto implements Serializable {
    @ApiModelProperty("标题")
    private String title;
}
