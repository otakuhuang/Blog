package com.otakuhuang.blog.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        return new ResultVO<>(ResultCode.FAILED, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        // 从异常对象中拿到 ObjectError 对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 提取错误提示信息进行返回
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
    }
}
