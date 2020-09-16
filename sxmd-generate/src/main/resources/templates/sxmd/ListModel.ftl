package com.sxmd.content.${folderName!''}.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Description: ${table.tableComment!''}所有数据列表实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "${table.tableComment!''}所有数据列表", description = "接收参数body")
@JsonIgnoreProperties({""})
public class ${fileName} extends ${table.tableNameToJavaName}AddModel {

}