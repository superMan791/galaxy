package com.zwsk.cloud.common.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: SysLog
 * @Description: 日志注解
 * @Author: 于志平
 * @CreateDate: 2019-08-29 10:25
 * @ModifiedDate:
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value();                 //日志的标题

    String[] column() default {};              //日志中保留的字段

    String[] columnHidden() default {};        //日志中禁用的字段,columnHidden的优先级高于column，如果两个字段发生冲突，columnHidden一定会生效
}
