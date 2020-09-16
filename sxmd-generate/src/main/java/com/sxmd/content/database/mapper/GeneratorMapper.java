package com.sxmd.content.database.mapper;

import com.sxmd.content.database.entity.ColumnEntity;
import com.sxmd.content.database.entity.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 通用接口
 *
 * @author cy
 * @date 2019年06月26日 10:24
 * Version 1.0
 */
public interface GeneratorMapper {

    /**
     * Description:   查询所有的表
     *
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.TableEntity>
     * @author cy
     * @date 2019/7/15 11:25
     */
    List<TableEntity> getAllTable(@Param("tableName") String tableName);

    /**
     * Description:   查询单个表
     *
     * @param tableName:
     * @return com.sxmd.content.database.entity.TableEntity
     * @author cy
     * @date 2019/7/15 11:25
     */
    TableEntity getOneTable(@Param("tableName") String tableName);

    /**
     * Description:   查询表中字段的信息
     *
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.ColumnEntity>
     * @author cy
     * @date 2019/7/15 11:25
     */
    List<ColumnEntity> getColumnsByTable(@Param("tableName") String tableName);

}
