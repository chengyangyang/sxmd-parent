package com.sxmd.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @author cy
 * @date 2020年05月11日 15:32
 * Version 1.0
 */
@Data
public abstract class BaseAbstractId {

    @ApiModelProperty(value = "主键")
    private Long id;

}
