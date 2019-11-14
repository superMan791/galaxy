/*
 *  Copyright (c) 2019-2019 ZWSK. All rights reserved.
 */

package com.cloud.galaxy.business.common.entity.po;

import java.util.Date;

import com.cloud.galaxy.common.core.base.BaseEntity;
import lombok.Data;

/**
 * @Package: com.cloud.galaxy.business.common.entity.po
 * * @ClassName: MessageTemplate
 * @Description: 消息模板
 * @Author: swei
 * @CreateDate: 2019-09-24
 * @Version: 1.0
 */
@Data
public class MessageTemplate extends BaseEntity<MessageTemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * 引用代码
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 说明，如参数代码
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 模板类型，0短信，1 邮件， 邮件统一使用富文本格式
     */
    private Integer type;


}
