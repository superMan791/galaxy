package com.cloud.galaxy.test.test.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birth;
    private LocalDateTime createTime;
    private LocalTime time;
    private BigDecimal balance;
    private List<Role> roleList;
}
