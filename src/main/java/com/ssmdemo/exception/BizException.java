package com.ssmdemo.exception;

/**
 * Created by dynamicniu on 2017/6/4.
 */
public class BizException extends RuntimeException {

    private final long serialVersionUID = 1L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
