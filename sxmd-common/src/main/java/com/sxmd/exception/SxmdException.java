package com.sxmd.exception;

import com.sxmd.constant.ResponseCodeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 自定义异常
 *
 * @author cy
 * @date 2019年06月19日 18:13
 * Version 1.0
 */
@Slf4j
@Getter
public class SxmdException extends RuntimeException {

    private static final long serialVersionUID = -7285597074867143121L;

    private final String message;
    private final ResponseCodeEnum responseCodeEnum;


    public SxmdException(String message) {
        log.warn(message);
        this.message = message;
        this.responseCodeEnum = null;
    }

    public SxmdException(ResponseCodeEnum responseCodeEnum) {
        this.message = null;
        log.warn(responseCodeEnum.getMessage());
        this.responseCodeEnum = responseCodeEnum;
    }

    public SxmdException(String message, Throwable cause) {
        log.warn(message, cause);
        this.message = message;
        this.responseCodeEnum = null;
    }

}
