package com.cloud.galaxy.business.common.service.impl;


import com.cloud.galaxy.business.common.dao.MessageTemplateRepository;
import com.cloud.galaxy.business.common.entity.po.MessageTemplate;
import com.cloud.galaxy.business.common.service.IMessageTemplateService;
import com.cloud.galaxy.common.core.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageTemplateServiceImpl extends BaseServiceImpl implements IMessageTemplateService {

    @Autowired
    MessageTemplateRepository messageTemplateRepository;

    /**
     * 通过模板构建消息内容，包括用户语言类型的标题和消息体
     *
     * @param param
     * @param templateCode
     * @return
     */
    @Override
    public Map<String, String> createMessageByTemplate(Map<String, String> param, String templateCode, String language) {
        MessageTemplate messageTemplate = messageTemplateRepository.findByCode(templateCode);
        String content = messageTemplate.getContent();
        //如果参数不为空，对参数进行替换
        if (param != null && param.size() > 0) {
            for (String key : param.keySet()) {
                content = content.replaceAll("\\$\\{" + key + "\\}", param.get(key));
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put("content", content);
        result.put("title", messageTemplate.getTitle());
        return result;
    }
}
