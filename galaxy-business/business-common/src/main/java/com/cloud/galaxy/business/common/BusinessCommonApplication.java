package com.cloud.galaxy.business.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.cloud.galaxy.business.common.dao")
public class BusinessCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessCommonApplication.class, args);
    }

}
