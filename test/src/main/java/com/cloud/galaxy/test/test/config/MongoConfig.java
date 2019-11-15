
package com.cloud.galaxy.test.test.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @PackageName: com.zwsk.cloud.amc.config
 * @ClassName: MongoConfig
 * @Description: mongodb事务配置
 * @Author: leilei
 * @CreateDate: 2019/9/28 14:07
 * @ModifiedDate:
 * @Version 1.0
 **/

@Configuration
@EnableTransactionManagement
public class MongoConfig {

    @Bean
    @Qualifier("mongodb")
    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Primary
    @Qualifier("mysql")
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

