package com.cloud.galaxy.common.core.base;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     * @Author brom
     * @param e :
     * @return com.zwsk.cloud.common.core.utils.Result
     * @throws
     * @Date 2019-08-28 08:07
     **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail(new BasicException(SystemErrorType.SYSTEM_ERROR,e.getMessage(),e));
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID);
    }
    /**
     * 验证异常
     * @Author brom
     * @param exception :
     * @return com.zwsk.cloud.common.core.utils.Result
     * @throws
     * @Date 2019-08-28 08:10
     **/
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn(fieldErrors.get(0).getDefaultMessage());
//        System.err.println(">>>>:errorMessage<:"+fieldErrors.get(0).getDefaultMessage());
        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID,fieldErrors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBindException(BindException exception) {
        List<String> defaultMsg = exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.warn(defaultMsg.get(0));
        /*
        StringBuilder msgBuilder = new StringBuilder();
        for(String error : defaultMsg) {
        	msgBuilder.append(error).append(";");
        }
        String errorMessage = msgBuilder.toString();
        if(errorMessage.length()>1){
            errorMessage = errorMessage.substring(0,errorMessage.length()-1);
        }
        */
        Result errorWebResult = new Result();
        errorWebResult.setCode(SystemErrorType.ARGUMENT_NOT_VALID.getCode());
        errorWebResult.setMsg(defaultMsg.get(0));
//    	errorWebResult.setMsg(errorMessage);
        return errorWebResult;
//        return Result.fail(SystemErrorType.ARGUMENT_NOT_VALID,defaultMsg.get(0));
    }

    /**
     * @Title: resolveConstraintViolationException
     * @Description: 处理校验信息 Violation 的错误
     * 用分号分隔表示有多个错误信息
     * @param ex
     * @return Result
     * @throws
     * @Author yangyiyuan
     * @Date 2019年9月26日 下午2:43:57
     */
    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  Result resolveConstraintViolationException(ConstraintViolationException ex){
        Result errorWebResult = new Result();
        errorWebResult.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//        String errorMessage = ex.getMessage();
        List<String> errorMessages = new ArrayList<String>();
        if(!CollectionUtils.isEmpty(constraintViolations)){
//            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation violation :constraintViolations){
                errorMessages.add(violation.getMessage());
//                msgBuilder.append(violation.getMessage()).append(";");
//                msgBuilder.append(violation.getPropertyPath() + ":" + violation.getMessage());
            }
//            errorMessage = msgBuilder.toString();
//            if(errorMessage.length()>1){
//                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
//            }
        }
        System.err.println(">>>>:resolveConstraintViolationException<"+errorMessages.get(0));
        errorWebResult.setCode(SystemErrorType.ARGUMENT_NOT_VALID.getCode());
        errorWebResult.setMsg(errorMessages.get(0));
        log.warn(JSONArray.toJSONString(errorMessages));
        return errorWebResult;
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadFileLimitException(MultipartException ex) {
        ex.printStackTrace();
        log.error("upload file size limit:{}", ex.getMessage());
        return Result.fail(SystemErrorType.MULTIPART_ERROR);
    }

    @ExceptionHandler(value = {BasicException.class})
    public Result baseException(BasicException ex) {
        log.error("base exception:{}", ex.getMessage());
        return Result.fail(ex.getErrorType());
    }

}