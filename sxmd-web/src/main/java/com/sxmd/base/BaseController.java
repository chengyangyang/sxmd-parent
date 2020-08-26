package com.sxmd.base;


import com.sxmd.constant.ResponseCodeEnum;

/**
 * Description: 返回数据对象
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
public class BaseController {

    public JsonResult success() {
        return new JsonResult();
    }

    public <T> JsonResult<T> success(T t) {
        return new JsonResult<T>(t);
    }

    public JsonResult fail(String message) {
        return new JsonResult().error(message);
    }

    public JsonResult fail(ResponseCodeEnum responseCodeEnum) {
        return new JsonResult().error(responseCodeEnum);
    }

}
