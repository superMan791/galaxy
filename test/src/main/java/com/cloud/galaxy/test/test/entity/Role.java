package com.cloud.galaxy.test.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String role;
    private BigDecimal balance;
}
