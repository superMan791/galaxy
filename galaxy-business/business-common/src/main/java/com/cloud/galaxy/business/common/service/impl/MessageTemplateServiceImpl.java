package com.cloud.galaxy.business.common.service.impl;


import com.cloud.galaxy.business.common.dao.MessageTemplateRepository;
import com.cloud.galaxy.business.common.entity.po.MessageTemplate;
import com.cloud.galaxy.business.common.entity.vo.GetMessageTemplateVo;
import com.cloud.galaxy.business.common.service.IMessageTemplateService;
import com.cloud.galaxy.common.core.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageTemplateServiceImpl extends BaseServiceImpl implements IMessageTemplateService {

    @Autowired
    MessageTemplateRepository messageTemplateRepository;

    @Override
    public MessageTemplate getByCode(String code) {
        return messageTemplateRepository.findByCode(code);
    }

    @Override
    public void delete(Long id) {
        messageTemplateRepository.deleteById(id.toString());
    }

    @Override
    public Boolean updateById(MessageTemplate messageTemplate) {
        return messageTemplateRepository.save(messageTemplate) != null;
    }

    @Override
    public Boolean insert(MessageTemplate messageTemplate) {
        return messageTemplateRepository.insert(messageTemplate) != null;
    }

    /**
     * 通过模板构建消息内容，包括用户语言类型的标题和消息体
     *
     * @param param
     * @param templateCode
     * @return
     */
    @Override
    public Map<String, String> createMessageByTemplate(Map<String, String> param, String templateCode) {
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

    @Override
    public GetMessageTemplateVo getById(Long id) {
        MessageTemplate messageTemplate = messageTemplateRepository.findById(id.toString()).get();
        GetMessageTemplateVo messageTemplateVo = new GetMessageTemplateVo();
        BeanUtils.copyProperties(messageTemplate, messageTemplateVo);
        return messageTemplateVo;
    }

    @Override
    public Page<GetMessageTemplateVo> findByPage(PageRequest pageRequest) {
        Page<MessageTemplate> page = messageTemplateRepository.findAll(pageRequest);
        Page<GetMessageTemplateVo> result = page.map((messageTemplate) -> {
            GetMessageTemplateVo getMessageTemplateVo = new GetMessageTemplateVo();
            BeanUtils.copyProperties(messageTemplate, getMessageTemplateVo);
            return getMessageTemplateVo;
        });
        return result;
    }
}
