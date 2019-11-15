package com.zwsk.cloud.common.log.entity.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: SysLogPO
 * @Description: 日志实体类
 * @Author: 于志平
 * @CreateDate: 2019-08-29 11:11
 * @ModifiedDate:
 * @Version: 1.0
 */
@Data
public class SysLogPo {
    private String title;               //日志标题
    private Long createBy;//请求人ID
    private LocalDateTime createTime;   //请求时间
    private String remoteIp;            //请求人IP地址
    private String requestUri;          //请求的URL地址
    private String method;              //请求类型(GET,PUT,POST,DELETE）
    private Object param;               //请求参数
    private String serviceId;           //请求的服务ID
    private String traceId;             //请求的链路id
    private Integer type;               //日志类型（正常日志，异常日志）
    private Integer time;                  //处理耗时
    private Object data;                //返回值
    private String exception;           //异常信息


}
