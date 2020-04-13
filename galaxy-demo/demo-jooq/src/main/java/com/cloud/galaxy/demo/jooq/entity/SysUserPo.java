package com.cloud.galaxy.demo.jooq.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SysUserPo {
    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDateTime createTime;
    private BigDecimal balance;
}
