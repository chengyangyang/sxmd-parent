package com.sxmd.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: 请求分页
 *
 * @author cy
 * @date 2020年04月23日 10:41
 * Version 1.0
 */
@Data
@ApiModel(value = "分页请求")
public class RequestPage {

    /**
     * 第几页
     */
    @ApiModelProperty(value = "页码", required = true)
    private Long pageNum;
    /**
     * 每页大小
     */
    @ApiModelProperty(value = "显示条数", required = true)
    private Long pageSize;

}
