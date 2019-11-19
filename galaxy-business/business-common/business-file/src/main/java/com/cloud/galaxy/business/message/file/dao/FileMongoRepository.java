package com.cloud.galaxy.business.message.file.dao;


import com.cloud.galaxy.business.message.file.entity.po.FileDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMongoRepository extends MongoRepository<FileDetail, String> {
    FileDetail findByContent(String content);
}
