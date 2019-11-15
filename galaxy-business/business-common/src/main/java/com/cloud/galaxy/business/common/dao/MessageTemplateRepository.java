package com.cloud.galaxy.business.common.dao;

import com.cloud.galaxy.business.common.entity.po.MessageTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageTemplateRepository extends MongoRepository<MessageTemplate, String> {
    MessageTemplate findByCode(String code);
}
