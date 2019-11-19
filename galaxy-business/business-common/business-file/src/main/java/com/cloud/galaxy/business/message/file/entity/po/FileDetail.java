package com.cloud.galaxy.business.message.file.entity.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("fileDetail")
public class FileDetail {
    // 原始文件名称
    private String fileName;
    // 文件存储地址，暂时使用fastdfs存储地址
    private String filePath;
    // MD5加密后的内容
    private String content;
    private long size;
    //状态， 默认1 正常， 0表示不可用
    private Integer status;

}
