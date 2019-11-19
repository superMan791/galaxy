package com.cloud.galaxy.business.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.cloud.galaxy.business.message.dao")
public class BusinessMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessMessageApplication.class, args);
    }

}
