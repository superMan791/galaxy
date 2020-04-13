package com.zwsk.cloud.common.log.annotation;

import java.lang.annotation.*;

/**
 * TODO 日志对象
 *
 * @author yzp
 * @date 2020/4/13 0013 8:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value();                 //日志的标题

    String[] column() default {};              //日志中保留的字段

    String[] columnHidden() default {};        //日志中禁用的字段,columnHidden的优先级高于column，如果两个字段发生冲突，columnHidden一定会生效
}
