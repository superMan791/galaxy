package com.cloud.galaxy.demo.jooq.controller;

import com.cloud.galaxy.demo.jooq.db.gen.Tables;
import com.cloud.galaxy.demo.jooq.db.gen.tables.SysRole;
import com.cloud.galaxy.demo.jooq.db.gen.tables.SysUser;
import com.cloud.galaxy.demo.jooq.db.gen.tables.records.SysUserRecord;
import com.cloud.galaxy.demo.jooq.entity.SysUserPo;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;

import static org.jooq.impl.DSL.*;

import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class DemoController {
    private SysUser sysUser = SysUser.SYS_USER;
    private SysRole sysRole = SysRole.SYS_ROLE;

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

    @GetMapping("insert1")
    public Boolean insert1(@RequestBody SysUserRecord sysUserRecord) {
        Integer result = dslContext
                .insertInto(Tables.SYS_USER).set(sysUserRecord)
                .execute();
        return result > 0;
    }

    @GetMapping("insert2")
    public Boolean insert2() {
        Integer result = dslContext
                .insertInto(Tables.SYS_USER)
                .set(sysUser.ID, 1l)
                .set(sysUser.NAME, "tom")
                .execute();
        return result > 0;
    }

    @GetMapping("selectList")
    public List<SysUserPo> selectList() {
        List<SysUserPo> list = dslContext.select().from(Tables.SYS_USER).fetch().map(record -> {
            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setId(record.get(sysUser.ID));
            sysUserPo.setBalance(record.get(sysUser.BALANCE));
            sysUserPo.setBirth(record.get(sysUser.BIRTH));
            sysUserPo.setCreateTime(record.get(sysUser.CREATE_TIME));
            sysUserPo.setName(record.get(sysUser.NAME));
            return sysUserPo;
        });
        return list;
    }

    @GetMapping("selectOne")
    public SysUserPo selectOne() {
        return dslContext.fetchOne(sysUser, sysUser.ID.eq(1l)).map(record -> {
            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setName(record.get(sysUser.NAME));
            sysUserPo.setId(record.get(sysUser.ID));
            sysUserPo.setCreateTime(record.get(sysUser.CREATE_TIME));
            sysUserPo.setBirth(record.get(sysUser.BIRTH));
            return sysUserPo;
        });
    }

    @GetMapping("select")
    public SysUserPo select() {
        return dslContext.select(sysUser.ID, sysUser.NAME, sysUser.BIRTH, sysRole.ROLE)
                .from(sysUser)
                .leftJoin(sysRole).on(sysUser.ID.eq(sysRole.USER_ID))
                .where(sysUser.ID.eq(1L)).fetchOne().map(record -> {
                    SysUserPo sysUserPo = new SysUserPo();
                    sysUserPo.setId(record.get(sysUser.ID));
                    sysUserPo.setName(record.get(sysUser.NAME));
                    return sysUserPo;
                });
    }

    @GetMapping("count")
    public List<Integer> count() {
        return dslContext.select(countDistinct(sysUser.ID).as("idCount")).from(sysUser)
                .groupBy(sysUser.BIRTH).fetch().stream().map(record -> {
                    Integer count = (Integer) record.get("idCount");
                    return count;
                }).collect(Collectors.toList());
    }

    @GetMapping("selectList2")
    public List<SysUserPo> selectList2(Long id) {
        SelectQuery<Record> query = dslContext.select().from(sysUser).getQuery();
        Optional.ofNullable(id).ifPresent(x -> {
            query.addConditions(sysUser.ID.eq(x));
        });
        List<SysUserPo> list = query.fetch().map(u -> {
            SysUserPo sysUserPo = new SysUserPo();
            sysUserPo.setId(u.get(sysUser.ID));
            sysUserPo.setName(u.get(sysUser.NAME));
            sysUserPo.setBirth(u.get(sysUser.BIRTH));
            return sysUserPo;
        });
        return list;
    }

    //用seek代替limit提示查询效率
    @GetMapping("selectListBySeek")
    public List<SysUserPo> selectListBySeek() {
        List<SysUserPo> sysUserPos = dslContext.select().from(sysUser).orderBy(sysUser.ID).seek(5l).limit(10).fetch()
                .map(record -> {
                    SysUserPo sysUserPo = new SysUserPo();
                    sysUserPo.setId(record.get(sysUser.ID));
                    sysUserPo.setName(record.get(sysUser.NAME));
                    sysUserPo.setBalance(record.get(sysUser.BALANCE));
                    return sysUserPo;
                });
        return sysUserPos;
    }

    //查询，并使用forUpdate锁定当前行，在事务提交之前，其他链接无法对这条数据进行修改和删除操作
    @GetMapping("selectForUpdate")
    public SysUserRecord selectForUpdate() {
        SysUserRecord sysUserRecord = dslContext.selectFrom(sysUser).where(sysUser.ID.eq(1l)).forUpdate().fetchOne();
        return sysUserRecord;
    }

    //乐观锁测试
    @GetMapping("update")
    public void update() {
        SysUserRecord record1 = dslContext.fetchOne(sysUser, sysUser.ID.eq(1l));
        SysUserRecord record2 = dslContext.fetchOne(sysUser, sysUser.ID.eq(1l));
        record1.setName("x");
        record2.setName("xx");
        log.info("record1的修改结果：" + (record1.store() > 0));
        log.info("record2的修改结果：" + (record2.store() > 0));
    }

    // onDuplicateKeyUpdate 如果不存在，添加记录，如果已存在，修改记录
    @GetMapping("onDuplicateKeyUpdate")
    public void onDuplicateKeyUpdate() {
        dslContext.insertInto(sysUser, sysUser.ID, sysUser.BALANCE).values(1l, new BigDecimal("12"))
                .onDuplicateKeyUpdate().set(sysUser.BALANCE, new BigDecimal("15")).execute();
    }

    //onDuplicateKeyIgnore 如果记录不存在，就插入记录。如果记录已存在，就不插入
    @GetMapping("onDuplicateKeyIgnore")
    public void onDuplicateKeyIgnore() {
        Integer result = dslContext.insertInto(sysUser, sysUser.ID, sysUser.BALANCE).values(1l, new BigDecimal(12)).onDuplicateKeyIgnore().execute();
        log.info(result);
    }
}
