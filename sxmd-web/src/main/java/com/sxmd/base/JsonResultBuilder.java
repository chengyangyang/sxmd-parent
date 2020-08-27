package com.sxmd.base;

import com.sxmd.constant.ResponseCodeEnum;

/**
 * Description:  json 返回工厂
 *
 * @author cy
 * @date 2020年08月27日 14:48
 * Version 1.0
 */
public class JsonResultBuilder {

    private JsonResultBuilder() {
    }

    public static JsonResult<Object> build() {
        JsonResult<Object> jsonResult = new JsonResult<>();
        ResponseCodeEnum responseEnum = ResponseCodeEnum.CODE_0000;
        jsonResult.setCode(responseEnum.name());
        jsonResult.setMessage(responseEnum.getMessage());
        return jsonResult;
    }

    public static JsonResult<Object> build(ResponseCodeEnum responseEnum) {
        JsonResult<java.lang.Object> jsonResult = new JsonResult<>();
        jsonResult.setCode(responseEnum.name());
        jsonResult.setMessage(responseEnum.getMessage());
        return jsonResult;
    }

    public static <T> JsonResult<T> build(ResponseCodeEnum responseEnum, T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(responseEnum.name());
        jsonResult.setMessage(responseEnum.getMessage());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static <T> JsonResult<T> build(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        ResponseCodeEnum responseEnum = ResponseCodeEnum.CODE_0000;
        jsonResult.setCode(responseEnum.name());
        jsonResult.setMessage(responseEnum.getMessage());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult<Object> build(ResponseCodeEnum responseEnum, String message) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        jsonResult.setCode(responseEnum.name());
        jsonResult.setMessage(message);
        return jsonResult;
    }


}
