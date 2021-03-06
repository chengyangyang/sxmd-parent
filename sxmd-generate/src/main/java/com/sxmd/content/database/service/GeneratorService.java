package com.sxmd.content.database.service;

import com.sxmd.content.database.entity.ColumnEntity;
import com.sxmd.content.database.entity.TableEntity;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 11:14
 * Version 1.0
 */

public interface GeneratorService {

    /**
     * Description:   获得所有的表
     *
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.TableEntity>
     * @author cy
     * @date 2019/6/26 17:16
     */
    List<TableEntity> getTableAll(String tableName);

    /**
     * Description:   初始化单个表
     *
     * @param tableName:
     * @return com.sxmd.content.database.entity.TableEntity
     * @author cy
     * @date 2019/6/26 17:17
     */
    public TableEntity getTableByTableNameAndInit(String tableName);

    /**
     * Description:  初始化表中的列
     *
     * @param isFilterColumns: 是否进行列的过滤
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.ColumnEntity>
     * @author cy
     * @date 2019/6/26 17:17
     */
    List<ColumnEntity> getColumnsByTableInit(boolean isFilterColumns, String tableName);

    /**
     * Description:   进行文件的生成   如果填写表名称，并且生成Java 文件，就会将字段和表的对象放进map中，并且文件名称会进行替换  否则按照原本的生成
     *
     * @param isFilterColumns: 是否进行列的过滤
     * @param tableName:       表名称   如果需要表名称拼接Java生成文件
     * @param templateName:    模板名称   和 FtlConfig 中 key名称相同
     * @param map:             值
     * @return void
     * @author cy
     * @date 2019/6/26 17:17
     */
    public void generatorJavaFile(boolean isFilterColumns, String tableName, String templateName, Map<String, Object> map);

}
