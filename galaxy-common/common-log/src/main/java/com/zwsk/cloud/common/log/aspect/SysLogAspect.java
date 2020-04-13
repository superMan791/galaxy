package com.zwsk.cloud.common.log.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwsk.cloud.common.log.annotation.SysLog;
import com.zwsk.cloud.common.log.entity.po.SysLogPo;
import com.zwsk.cloud.common.log.util.SysLogUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * TODO 日志拦截器
 *
 * @author yzp
 * @date 2020/4/13 0013 8:34
 */
@Aspect
@Component
@Log4j2
public class SysLogAspect {
    @Value("${sys.log.enabled:true}")
    private Boolean enabled;
    @Value("${sys.log.column:title,createBy,createTime,remoteIp,requestUri,method,param,serviceId,traceId,type,time,data,exception}")
    private String[] column;
    @Value("${sys.log.columnHidden:}")
    private String[] columnHidden;
    @Value("${spring.application.name:}")
    private String serviceId;
    @Value("${sys.log.kafka.topic:sysLog}")
    private String topic;
    @Resource
    private KafkaTemplate kafkaTemplate;
    @Resource
    private ObjectMapper objectMapper;


    /**
     * 环绕切面，捕获方法的日志信息
     *
     * @param point
     * @param sysLog
     * @return {@link Object}
     * @throws
     * @author yzp
     * @date 2020/4/13 0013 8:44
     */
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取链路跟踪的链路id,作为请求id,用来标记一次调用链
        String traceId = request.getHeader("x-b3-traceid");
        //如果sysLog.enabled=false，表示不启用日志收集，该参数的值可以在bootstrap.yaml或者application.yaml中配置
        if (enabled == false) {
            return point.proceed();
        }
        String[] col = column;
        String[] colHidden = columnHidden;
        //如果用户在@SysLog注解中指定了需要保存的日志参数，就使用注解的配置覆盖全局配置
        if (sysLog.column().length > 0) {
            col = sysLog.column();
        }
        //如果用户在@SysLog注解中指定了需要禁用的字段，就使用注解的配置覆盖全局配置
        if (sysLog.columnHidden().length > 0) {
            colHidden = sysLog.columnHidden();
        }
        SysLogPo sysLogPO = new SysLogPo();
        //方法执行前，在日志中记录请求参数
        SysLogUtil.before(sysLogPO, sysLog.value(), serviceId, traceId, Arrays.asList(col), Arrays.asList(colHidden));
        Object obj = null;
        try {
            obj = point.proceed();
        } catch (Throwable throwable) {
            //方法执行出现异常，在日志中记录异常信息
            SysLogUtil.afterThrowing(sysLogPO, throwable, Arrays.asList(col), Arrays.asList(colHidden));
            //重新将异常抛出去，防止数据库事务不回滚
            throw throwable;
        }
        //方法正常执行结束，在日志中记录响应信息
        SysLogUtil.afterRunning(sysLogPO, obj, Arrays.asList(col), Arrays.asList(colHidden));
        kafkaTemplate.send(topic, objectMapper.writeValueAsString(sysLogPO));
        return obj;
    }

}
