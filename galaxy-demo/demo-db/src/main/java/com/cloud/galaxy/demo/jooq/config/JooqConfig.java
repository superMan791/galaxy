package com.cloud.galaxy.demo.jooq.config;


import org.jooq.conf.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {

    @Bean
    public Settings settings() {
        Settings settings = new Settings();
        //开启乐观锁
        settings.withExecuteWithOptimisticLocking(true)
                .withUpdateRecordTimestamp(true);
        return settings;
    }
}
