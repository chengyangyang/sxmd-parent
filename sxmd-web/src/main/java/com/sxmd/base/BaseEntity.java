package com.sxmd.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Description:  表的公共字段
 *
 * @author cy
 * @date 2019年08月05日 16:41
 * Version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends BaseAbstractId {

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
