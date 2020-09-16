package com.sxmd.content.${folderName!''}.entity;

import com.sxmd.base.BaseAbstractId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

<#list importPackageNumer as li>
    <#if li??>
${(li)!}
    </#if>
</#list>


/**
 * Description: ${table.tableComment!''}实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ToString(callSuper = true)
@TableName("${table.tableName}")
public class ${fileName} extends BaseAbstractId {

    <#list columns as li>
    <#if li.columnComment??>
    /**
     * ${li.columnComment}
     */
    </#if>
    @TableField(value = "${li.columnName!}")
    private ${li.columnTypeToJava} ${li.columnNameToJava};

    </#list>

}
