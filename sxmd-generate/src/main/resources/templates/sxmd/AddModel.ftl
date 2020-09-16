package com.sxmd.content.${folderName!''}.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#list importPackageNumer as li>
<#if li??>
${(li)!}
</#if>
</#list>

/**
 * Description: 添加${table.tableComment!''}实体
 *              字段需要修改，需移至EditModel中
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "新增${table.tableComment!''}实体", description = "接收参数body")
@JsonIgnoreProperties({"id"})
public class ${fileName} extends ${table.tableNameToJavaName}EditModel{


}