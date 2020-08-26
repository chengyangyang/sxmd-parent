package com.sxmd.base;

import com.sxmd.constant.ResponseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 返回数据对象，如果返回结果指定泛型，swagger 就会显示
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
@Data
@ApiModel(value = "请求结果")
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 2551611347713465699L;

    @ApiModelProperty(value = "返回码")
    private String code;
    @ApiModelProperty(value = "结果")
    private String message;
    @ApiModelProperty(value = "数据内容")
    private T data;

    public JsonResult() {
        ResponseCodeEnum success = ResponseCodeEnum.CODE_0000;
        this.code = success.name();
        this.message = success.getMessage();
    }

    public JsonResult(ResponseCodeEnum responseEnum, T data) {
        this.code = responseEnum.name();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public JsonResult(T data) {
        ResponseCodeEnum success = ResponseCodeEnum.CODE_0000;
        this.code = success.name();
        this.message = success.getMessage();
        this.data = data;
    }

    public JsonResult error(ResponseCodeEnum responseEnum) {
        this.code = responseEnum.name();
        this.message = responseEnum.getMessage();
        return this;
    }

    public JsonResult error(String message) {
        this.code = ResponseCodeEnum.CODE_9999.name();
        this.message = message;
        return this;
    }

    public JsonResult error(ResponseCodeEnum responseEnum, String message) {
        this.code = responseEnum.name();
        this.message = message;
        return this;
    }

}
