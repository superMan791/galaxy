package com.cloud.galaxy.demo.poi;

import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.cloud.galaxy.demo.poi.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.export();
    }

    /**
     * excel导出
     */
    public void export() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(Long.MAX_VALUE);
            user.setBalance(new BigDecimal("12.00000011111111"));
            user.setBirth(LocalDate.of(1992, 1, 21));
            user.setCreateTime(LocalDateTime.now());
            user.setName("tom");
            list.add(user);
        }
        // 通过工具类创建writer
        BigExcelWriter writer = ExcelUtil.getBigWriter("d:/writeBeanTest.xlsx");

//自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("id", "编号");
        writer.addHeaderAlias("birth", "生日");
        writer.addHeaderAlias("balance", "存款");
        writer.addHeaderAlias("createTime", "创建时间");

// 合并单元格后的标题行，使用默认标题样式
        writer.merge(5, "用户信息表");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
// 关闭writer，释放内存
        writer.close();
    }

    /**
     * excel导入
     */
    public void imports() {
        ExcelReader reader = ExcelUtil.getReader("d:/writeBeanTest.xlsx");
        List<List<Object>> readAll = reader.read();
    }
}
