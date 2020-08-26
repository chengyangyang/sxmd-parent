package com.sxmd.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 对mybatis-plus 分页进行封装
 *
 * @author cy
 * @date 2019年09月20日 15:58
 * Version 1.0
 */
@Data
@ApiModel(value = "分页数据")
public class BasePage<T> implements Serializable {

    private static final long serialVersionUID = 8339884422392974122L;

    @ApiModelProperty(value = "分页数据内容")
    private List<T> content;

    @ApiModelProperty(value = "分页信息")
    private Pageable pageable;


}
