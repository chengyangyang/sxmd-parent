package com.sxmd.content.${folderName!''}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxmd.base.page.PageStart;
import com.sxmd.content.${folderName!''}.entity.${table.tableNameToJavaName}Entity;
import com.sxmd.content.${folderName!''}.model.${table.tableNameToJavaName}ListResponseModel;
import com.sxmd.content.${folderName!''}.model.${table.tableNameToJavaName}ListRequestModel;
import com.sxmd.content.${folderName!''}.model.${table.tableNameToJavaName}ListModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Description: ${table.tableComment!''} dao接口
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
public interface ${fileName} extends BaseMapper<${table.tableNameToJavaName}Entity> {

    /**
     * Description: 列表查询
     * @author sxmd
     * @param listRequestModel:
     * @return java.util.List
     * @date
     */
    List<${table.tableNameToJavaName}ListResponseModel> find${table.tableNameToJavaName}List(PageStart<${table.tableNameToJavaName}ListResponseModel> pageStart,
                                                                                            ${table.tableNameToJavaName}ListRequestModel listRequestModel);

    /**
     * Description: 获得所有数据
     * @author sxmd
     * @param :
     * @return java.util.List
     * @date
     */
    List<${table.tableNameToJavaName}ListModel> findAllList();

    /**
     * Description: 批量新增
     * @author sxmd
     * @param entityList:
     * @return long
     * @date
     */
    long batchInsert(List<${table.tableNameToJavaName}Entity> entityList);


    /**
     * Description: 批量新增
     * @author sxmd
     * @param ids:
     * @return long
     * @date
     */
    long batchDeleteByIds(@Param("ids") String[] ids);

}