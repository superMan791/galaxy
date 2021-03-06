/*
 * This file is generated by jOOQ.
 */
package com.cloud.galaxy.demo.jooq.db.gen.tables.records;


import com.cloud.galaxy.demo.jooq.db.gen.tables.SysUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import com.cloud.galaxy.demo.jooq.entity.SysUserPo;
import org.jooq.*;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysUserRecord extends UpdatableRecordImpl<SysUserRecord> implements Record6<Long, String, LocalDate, LocalDateTime, BigDecimal, LocalDateTime> {

    private static final long serialVersionUID = 395676893;

    /**
     * Setter for <code>demo.sys_user.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>demo.sys_user.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>demo.sys_user.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>demo.sys_user.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>demo.sys_user.birth</code>.
     */
    public void setBirth(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>demo.sys_user.birth</code>.
     */
    public LocalDate getBirth() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>demo.sys_user.create_time</code>.
     */
    public void setCreateTime(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>demo.sys_user.create_time</code>.
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>demo.sys_user.balance</code>.
     */
    public void setBalance(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>demo.sys_user.balance</code>.
     */
    public BigDecimal getBalance() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>demo.sys_user.update_time</code>.
     */
    public void setUpdateTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>demo.sys_user.update_time</code>.
     */
    public LocalDateTime getUpdateTime() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, LocalDate, LocalDateTime, BigDecimal, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, LocalDate, LocalDateTime, BigDecimal, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return SysUser.SYS_USER.ID;
    }

    @Override
    public Field<String> field2() {
        return SysUser.SYS_USER.NAME;
    }

    @Override
    public Field<LocalDate> field3() {
        return SysUser.SYS_USER.BIRTH;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return SysUser.SYS_USER.CREATE_TIME;
    }

    @Override
    public Field<BigDecimal> field5() {
        return SysUser.SYS_USER.BALANCE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return SysUser.SYS_USER.UPDATE_TIME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDate component3() {
        return getBirth();
    }

    @Override
    public LocalDateTime component4() {
        return getCreateTime();
    }

    @Override
    public BigDecimal component5() {
        return getBalance();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdateTime();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDate value3() {
        return getBirth();
    }

    @Override
    public LocalDateTime value4() {
        return getCreateTime();
    }

    @Override
    public BigDecimal value5() {
        return getBalance();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdateTime();
    }

    @Override
    public SysUserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SysUserRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public SysUserRecord value3(LocalDate value) {
        setBirth(value);
        return this;
    }

    @Override
    public SysUserRecord value4(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    public SysUserRecord value5(BigDecimal value) {
        setBalance(value);
        return this;
    }

    @Override
    public SysUserRecord value6(LocalDateTime value) {
        setUpdateTime(value);
        return this;
    }

    @Override
    public SysUserRecord values(Long value1, String value2, LocalDate value3, LocalDateTime value4, BigDecimal value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysUserRecord
     */
    public SysUserRecord() {
        super(SysUser.SYS_USER);
    }

    /**
     * Create a detached, initialised SysUserRecord
     */
    public SysUserRecord(Long id, String name, LocalDate birth, LocalDateTime createTime, BigDecimal balance, LocalDateTime updateTime) {
        super(SysUser.SYS_USER);

        set(0, id);
        set(1, name);
        set(2, birth);
        set(3, createTime);
        set(4, balance);
        set(5, updateTime);
    }
}
