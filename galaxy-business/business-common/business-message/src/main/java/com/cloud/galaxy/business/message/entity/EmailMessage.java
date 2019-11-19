package com.cloud.galaxy.business.message.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class EmailMessage extends AbstractMessage implements Serializable {
    private String title;        //标题
    private String content;     //内容
    private String[] to;        //接收人
    private Map<String, String> inline;  //嵌入到html内容中的图片， key为绑定的变量名，value为图片地址
    private Map<String, String> attachment; //key为附件的id,value为附件的地址
}
