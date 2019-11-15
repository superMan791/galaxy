package com.zwsk.cloud.common.log.contanst;

/**
 * @ClassName: SysLogColumnEnum
 * @Description: 日志字段枚举类，在@SysLog注解中保留或禁用某个字段时使用
 * @Author: 于志平
 * @CreateDate: 2019-08-29 10:25
 * @ModifiedDate:
 * @Version: 1.0
 */
public interface SysLogColumnEnum {
    String TITLE = "title";
    String CREATE_BY = "createBy";
    String CREATE_TIME = "createTime";
    String REMOTE_IP = "remoteIp";
    String REQUEST_URI = "requestUri";
    String METHOD = "method";
    String PARAM = "param";
    String SERVICE_ID = "serviceId";
    String TRACE_ID = "traceId";
    String TYPE = "type";
    String TIME = "time";
    String DATA = "data";
    String EXCEPTION = "exception";

}
