package com.sxmd.handle;

import com.sxmd.base.JsonResult;
import com.sxmd.base.JsonResultBuilder;
import com.sxmd.constant.ResponseCodeEnum;
import com.sxmd.exception.SxmdException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Description:  自定义异常handler
 *
 * @author cy
 * @date 2020年06月04日 11:29
 * Version 1.0
 */
@RestControllerAdvice
public class CustomExceptionHandle {

    @ExceptionHandler(SxmdException.class)
    public JsonResult<Object> handlesxmdException(SxmdException e) {
        if (e.getResponseCodeEnum() != null) {
            return JsonResultBuilder.build(e.getResponseCodeEnum());
        } else {
            return JsonResultBuilder.build(ResponseCodeEnum.CODE_9999, e);
        }
    }

    /**
     * Description:    jsr303  异常统一处理
     *
     * @param e:
     * @return com.sxmd.base.AjaxResult
     * @author cy
     * @date 2020/7/2 19:27
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldErrors.size(); i++) {
            stringBuilder.append("[" + fieldErrors.get(i).getDefaultMessage() + "]");
        }
        return JsonResultBuilder.build(ResponseCodeEnum.CODE_9999, stringBuilder.toString());
    }


}



