package com.cloud.galaxy.common.core.base;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        e.printStackTrace();
        return R.fail();
    }

    /**
     * post请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.fail(defaultMessage);
    }

    /**
     * get请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException e){
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.fail(defaultMessage);
    }

    /**
     * 请求方法中校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R constraintViolationExceptionHandler(ConstraintViolationException e){
        //获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return R.fail(message);
    }
}