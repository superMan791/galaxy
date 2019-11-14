package com.cloud.galaxy.test.test.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class User {
    private Long id;
    @NotNull(message = "用户名不能为空")
    private String name;
    @Max(value = 100,message = "年龄不能超过100")
    private Integer age;
    private LocalDate birth;
    private LocalDateTime createTime;
    private LocalTime time;
    private BigDecimal balance;
    private List<Role> roleList;
}
