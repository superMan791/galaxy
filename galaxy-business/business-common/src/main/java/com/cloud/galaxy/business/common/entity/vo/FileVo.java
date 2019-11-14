package com.cloud.galaxy.business.common.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FileVo {
    @ApiModelProperty("原始文件名称")
    private String fileName;
    @ApiModelProperty("文件存储地址，暂时使用fastdfs存储地址")
    private String filePath;
    @ApiModelProperty("MD5加密后的内容")
    private String content;
    @ApiModelProperty("文件大小")
    private long size;
    @ApiModelProperty("状态， 默认1 正常， 0表示不可用")
    private Integer status;
    @ApiModelProperty("文件的地址前缀，文件的绝对路径=contextPath+filePath")
    private String contextPath;
}
