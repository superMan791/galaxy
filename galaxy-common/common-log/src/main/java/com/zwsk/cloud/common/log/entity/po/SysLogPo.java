package com.zwsk.cloud.common.log.entity.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO 日志对象类
 *
 * @author yzp
 * @date 2020/4/13 0013 8:34
 */
@Data
public class SysLogPo {
    /**
     * 标题
     */
    private String title;
    /**
     * 请求人ID
     */
    private Long createBy;
    /**
     * 请求时间
     */
    private LocalDateTime createTime;
    /**
     * 请求人IP地址
     */
    private String remoteIp;
    /**
     * 请求的URL地址
     */
    private String requestUri;
    /**
     * 请求类型(GET,PUT,POST,DELETE）
     */
    private String method;
    /**
     * 请求参数
     */
    private Object param;
    /**
     * 请求的服务ID
     */
    private String serviceId;
    /**
     * 请求的链路id
     */
    private String traceId;
    /**
     * 日志类型（正常日志，异常日志）
     */
    private Integer type;
    /**
     * 处理耗时
     */
    private Integer time;
    /**
     * 返回值
     */
    private Object data;
    /**
     * 异常信息
     */
    private String exception;


}
