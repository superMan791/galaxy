package com.cloud.galaxy.business.common.service;

import com.cloud.galaxy.common.core.base.IBaseService;

import java.util.Map;

public interface IMessageTemplateService extends IBaseService {
    /**
     * 通过模板构建消息内容，包括用户语言类型的标题和消息体
     *
     * @param param
     * @param templateCode
     * @return
     */
    Map<String, String> createMessageByTemplate(Map<String, String> param, String templateCode, String language);
}
