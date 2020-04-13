package com.cloud.galaxy.common.core.base;

import com.cloud.galaxy.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO 返回值封装
 *
 * @author yzp
 * @date 2020/4/13 0013 8:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}