package com.cloud.galaxy.business.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @PackageName: com.zwsk.cloud.demo.config
 * @ClassName: MongoConfig
 * @Description: mongodb事务配置
 * @Author: 于志平
 * @CreateDate: 2019-09-23 15:35
 * @ModifiedDate:
 * @Version: 1.0
 */
@Configuration
public class MongoConfig {
    @Primary
    @Bean
    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
