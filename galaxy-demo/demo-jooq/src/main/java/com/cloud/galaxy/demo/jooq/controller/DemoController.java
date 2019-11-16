//package com.cloud.galaxy.demo.jooq.controller;
//
//import com.cloud.galaxy.demo.jooq.db.gen.Tables;
//import com.cloud.galaxy.demo.jooq.db.gen.tables.SysUser;
//import com.cloud.galaxy.demo.jooq.entity.SysUserPo;
//import org.jooq.DSLContext;
//import org.jooq.Record;
//import org.jooq.Result;
//import org.jooq.impl.DSL;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@RestController
//public class DemoController {
//    public static void main(String[] args) throws Exception {
//    }
//
//    @Autowired
//    private DSLContext dslContext;
//
//    @GetMapping("insert")
//    public void insert() {
//        SysUser sysUser = SysUser.SYS_USER;
//        dslContext
//                .insertInto(Tables.SYS_USER, sysUser.ID, sysUser.NAME, sysUser.BALANCE, sysUser.BIRTH, sysUser.CREATE_TIME)
//                .values(1L, "tom", new BigDecimal("12.00"), LocalDate.of(1993, 9, 16), LocalDateTime.now())
//                .execute();
//
//    }
//
//    public SysUserPo updateOne() {
//        dslContext.update(DSL.table("sys_user"));
//        return null;
//    }
//
//    public void selectBySql() {
//        // Use your favourite tool to construct SQL strings:
//        String sql = "SELECT title, first_name, last_name FROM book JOIN author ON book.author_id = author.id " +
//                "WHERE book.published_in = 1984";
//
//// Fetch results using jOOQ
//        Result<Record> result = dslContext.fetch(sql);
//    }
//}
