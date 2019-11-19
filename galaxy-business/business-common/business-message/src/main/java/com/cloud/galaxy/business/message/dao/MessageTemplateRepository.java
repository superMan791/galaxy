package com.cloud.galaxy.business.message.dao;

import com.cloud.galaxy.business.message.entity.po.MessageTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateRepository extends MongoRepository<MessageTemplate, String> {
    MessageTemplate findByCode(String code);
}
