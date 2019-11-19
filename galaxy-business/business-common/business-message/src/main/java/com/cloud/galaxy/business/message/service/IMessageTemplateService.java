package com.cloud.galaxy.business.message.service;

import com.cloud.galaxy.business.message.entity.po.MessageTemplate;
import com.cloud.galaxy.business.message.entity.vo.GetMessageTemplateVo;
import com.cloud.galaxy.common.core.base.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface IMessageTemplateService extends IBaseService {
    /**
     * 通过模板构建消息内容，包括用户语言类型的标题和消息体
     *
     * @param param
     * @param templateCode
     * @return
     */
    Map<String, String> createMessageByTemplate(Map<String, String> param, String templateCode);

    MessageTemplate getByCode(String code);

    Boolean insert(MessageTemplate messageTemplate);

    void delete(String id);

    Boolean updateById(MessageTemplate messageTemplate);

    GetMessageTemplateVo getById(String id);

    Page<GetMessageTemplateVo> findByPage(PageRequest pageRequest,String title);
}
