package com.cloud.galaxy.demo.poi.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String name;
    private BigDecimal balance;
    private LocalDate birth;
    private LocalDateTime createTime;
}
