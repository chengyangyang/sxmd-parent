package com.sxmd.base;


import com.sxmd.constant.ResponseCodeEnum;

/**
 * Description: 公共controller
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
public class BaseController {

    public JsonResult<Object> success() {
        return JsonResultBuilder.build();
    }

    public <T> JsonResult<T> success(T t) {
        return JsonResultBuilder.build(t);
    }

    public JsonResult<Object> fail(String message) {
        return JsonResultBuilder.build(ResponseCodeEnum.CODE_9999, message);
    }

    public JsonResult<Object> fail(ResponseCodeEnum responseCodeEnum) {
        return JsonResultBuilder.build(responseCodeEnum);
    }

}
