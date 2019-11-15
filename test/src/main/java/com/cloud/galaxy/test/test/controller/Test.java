package com.cloud.galaxy.test.test.controller;

import org.jooq.DSLContext;
import org.jooq.InsertQuery;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    DSLContext dslContext;
    @RequestMapping("test")
    @Transactional(transactionManager = "mysql")
    public void test(){
        test1();
        test2();
        throw new RuntimeException();
    }
    @Transactional(transactionManager = "mongodb")
    public void test1(){
        Map<String,Object> param=new HashMap<>();
        param.put("id",1L);
        param.put("username","tom");
        param.put("password","123456");
        mongoTemplate.save(param,"test111");
    }
    @Transactional(transactionManager = "mysql")
    public void test2(){
        Table<Record> table = DSL.table("sys_user");
        InsertQuery<Record> insertQuery=dslContext.insertQuery(table);
        insertQuery.addValue(DSL.field("username"),"tom");
        insertQuery.addValue(DSL.field("id"),1L);
        insertQuery.execute();
    }
}
