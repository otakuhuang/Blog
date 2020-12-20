package com.otakuhuang.blog.common;

public class APIException extends RuntimeException {
    private int code;
    private String message;

    public APIException() {
        this(1000, "接口错误");
    }

    public APIException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
