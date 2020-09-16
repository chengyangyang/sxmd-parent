package com.sxmd.content.${folderName!''}.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Description: 编辑${table.tableComment!''}实体
 *              非修改字段需要移至 AddModel中
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "编辑${table.tableComment!''}实体", description = "接收参数body")
public class ${fileName} {

    @ApiModelProperty(name = "主键,新增不需要要，更新必填")
    private String id;

<#list columns as li>
    @ApiModelProperty(value = "${(li.columnComment)!}")
    private ${li.columnTypeToJava} ${li.columnNameToJava};

</#list>

}