package com.cloud.galaxy;

import cn.msuno.swagger.spring.boot.autoconfigure.annotation.EnableJavadocSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJavadocSwagger2
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
