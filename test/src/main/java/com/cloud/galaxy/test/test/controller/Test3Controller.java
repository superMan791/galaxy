package com.cloud.galaxy.test.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("測試")
@RequestMapping("test3")
public class Test3Controller {

    @ApiOperation("测试1")
    @GetMapping("test1")
    public String test1(String id) {
        return id;
    }

    @ApiOperation("测试2")
    @PostMapping("test2")
    public String test2() {
        return "dsfsdfsdf";
    }

    @ApiOperation("测试3")
    @DeleteMapping("test3")
    public String test3(String id) {
        return id;
    }

    @ApiOperation("测试4")
    @PutMapping("test4")
    public String test4() {
        return "fdssdfsdffd";
    }
}
