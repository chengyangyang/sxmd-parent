package com.sxmd.content.${folderName!''}.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#list importPackageNumer as li>
    <#if li??>
${(li)!}
    </#if>
</#list>

/**
 * Description: ${table.tableComment!''}详情实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "${table.tableComment!''}详情实体")
public class ${fileName} {

    <#list columns as li>
    @ApiModelProperty(value = "${(li.columnComment)!}")
    private ${li.columnTypeToJava} ${li.columnNameToJava};
    </#list>

}