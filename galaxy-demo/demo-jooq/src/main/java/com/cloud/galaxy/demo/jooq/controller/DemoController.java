package com.cloud.galaxy.demo.jooq.controller;

import com.cloud.galaxy.demo.jooq.db.gen.Tables;
import com.cloud.galaxy.demo.jooq.db.gen.tables.SysUser;
import com.cloud.galaxy.demo.jooq.db.gen.tables.records.SysUserRecord;
import com.cloud.galaxy.demo.jooq.entity.SysUserPo;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Select;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DemoController {
    SysUser sysUser = SysUser.SYS_USER;

    @Autowired
    private DSLContext dslContext;

    @GetMapping("insert")
    public Boolean insert() {
        Integer result = dslContext
                .insertInto(Tables.SYS_USER, sysUser.ID, sysUser.NAME, sysUser.BALANCE, sysUser.BIRTH, sysUser.CREATE_TIME)
                .values(1L, "tom", new BigDecimal("12.00"), LocalDate.of(1993, 9, 16), LocalDateTime.now())
                .execute();
        return result > 0;
    }

    @GetMapping("selectList")
    public List<SysUserPo> selectList() {
        List<SysUserPo> list =dslContext.select().from(Tables.SYS_USER).fetch()
                .stream().map(record -> {
                    SysUserPo sysUserPo=new SysUserPo();
                    sysUserPo.setId(record.get(sysUser.ID));
                    sysUserPo.setBalance(record.get(sysUser.BALANCE));
                    sysUserPo.setBirth(record.get(sysUser.BIRTH));
                    sysUserPo.setCreateTime(record.get(sysUser.CREATE_TIME));
                    sysUserPo.setName(record.get(sysUser.NAME));
                    return sysUserPo;
                }).collect(Collectors.toList());
        return list;
    }

    public void selectBySql() {
        // Use your favourite tool to construct SQL strings:
        String sql = "SELECT title, first_name, last_name FROM book JOIN author ON book.author_id = author.id " +
                "WHERE book.published_in = 1984";

// Fetch results using jOOQ
        Result<Record> result = dslContext.fetch(sql);
    }
}
