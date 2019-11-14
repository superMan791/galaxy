package com.cloud.galaxy.business.common.dao;

import com.cloud.galaxy.business.common.entity.po.FilePo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMongoRepository extends MongoRepository<FilePo,String> {
    FilePo findByContent(String content);
}
