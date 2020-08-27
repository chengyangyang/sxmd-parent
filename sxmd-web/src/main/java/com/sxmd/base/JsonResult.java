package com.sxmd.base;

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

}
