package com.zwsk.cloud.common.log.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zwsk.cloud.common.log.contanst.SysLogColumnEnum;
import com.zwsk.cloud.common.log.entity.po.SysLogPo;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: SysLogUtil
 * @Description: 日志收集工具类
 * @Author: 于志平
 * @CreateDate: 2019-08-29 11:12
 * @ModifiedDate:
 * @Version: 1.0
 */
@Component
public class SysLogUtil {

    /**
     * 方法执行前，将请求相关信息保存到日志中
     *
     * @param sysLogPO
     * @param title
     */
    public static void before(SysLogPo sysLogPO, String title, String serviceId, String traceId, List<String> column, List<String> columnHidden) throws JsonProcessingException {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        if (column.contains(SysLogColumnEnum.TITLE) && !columnHidden.contains(SysLogColumnEnum.TITLE)) {
            sysLogPO.setTitle(title);
        }
        if (column.contains(SysLogColumnEnum.CREATE_BY) && !columnHidden.contains(SysLogColumnEnum.CREATE_BY)) {

            sysLogPO.setCreateBy(1L);

        }
        if (column.contains(SysLogColumnEnum.CREATE_TIME) && !columnHidden.contains(SysLogColumnEnum.CREATE_TIME)) {
            sysLogPO.setCreateTime(LocalDateTime.now());
        }
        if (column.contains(SysLogColumnEnum.REMOTE_IP) && !columnHidden.contains(SysLogColumnEnum.REMOTE_IP)) {
            sysLogPO.setRemoteIp(request.getRemoteAddr());
        }
        if (column.contains(SysLogColumnEnum.REQUEST_URI) && !columnHidden.contains(SysLogColumnEnum.REQUEST_URI)) {
            sysLogPO.setRequestUri(request.getRequestURI());
        }
        if (column.contains(SysLogColumnEnum.METHOD) && !columnHidden.contains(SysLogColumnEnum.METHOD)) {
            sysLogPO.setMethod(request.getMethod());
        }
        if (column.contains(SysLogColumnEnum.PARAM) && !columnHidden.contains(SysLogColumnEnum.PARAM)) {
            sysLogPO.setParam(request.getParameterMap());
        }
        if (column.contains(SysLogColumnEnum.SERVICE_ID) && !columnHidden.contains(SysLogColumnEnum.SERVICE_ID)) {
            sysLogPO.setServiceId(serviceId);
        }
        if (column.contains(SysLogColumnEnum.TRACE_ID) && !columnHidden.contains(SysLogColumnEnum.TRACE_ID)) {
            sysLogPO.setTraceId(traceId);
        }
    }

    /**
     * 方法正常结束，将相应信息保存到日志中
     *
     * @param sysLogPO
     * @param obj
     * @param column
     * @param columnHidden
     */
    public static void afterRunning(SysLogPo sysLogPO, Object obj, List<String> column, List<String> columnHidden) throws JsonProcessingException {
        if (column.contains(SysLogColumnEnum.TYPE) && !columnHidden.contains(SysLogColumnEnum.TYPE)) {
            sysLogPO.setType(0);
        }
        if (column.contains(SysLogColumnEnum.TIME) && !columnHidden.contains(SysLogColumnEnum.TIME)) {
            if (sysLogPO.getCreateTime() != null) {
                sysLogPO.setTime(LocalDateTime.now().getSecond() - sysLogPO.getCreateTime().getSecond());
            }
        }
        if (column.contains(SysLogColumnEnum.DATA) && !columnHidden.contains(SysLogColumnEnum.DATA)) {
            sysLogPO.setData(obj);
        }
    }

    /**
     * 方法执行异常，将异常信息保存到日志中
     *
     * @param sysLogPO
     * @param throwable
     * @param column
     * @param columnHidden
     */
    public static void afterThrowing(SysLogPo sysLogPO, Throwable throwable, List<String> column, List<String> columnHidden) {
        if (column.contains(SysLogColumnEnum.TYPE) && !columnHidden.contains(SysLogColumnEnum.TYPE)) {
            sysLogPO.setType(1);
        }
        if (column.contains(SysLogColumnEnum.TIME) && !columnHidden.contains(SysLogColumnEnum.TIME)) {
            sysLogPO.setTime(LocalDateTime.now().getSecond() - sysLogPO.getCreateTime().getSecond());
        }
        if (column.contains(SysLogColumnEnum.EXCEPTION) && !columnHidden.contains(SysLogColumnEnum.EXCEPTION)) {
            sysLogPO.setException(throwable.getMessage());
        }
    }
}
