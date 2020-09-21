package com.sxmd.content.${folderName!''}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxmd.base.BasePage;
import com.sxmd.base.RequestPage;
import com.sxmd.content.${folderName!''}.entity.${table.tableNameToJavaName}Entity;
import com.sxmd.content.${folderName!''}.model.*;

import java.util.List;

/**
 * Description: ${table.tableComment!''}接口
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
public interface ${fileName} extends IService<${table.tableNameToJavaName}Entity> {

    /**
     * Description:  ${table.tableComment!''}-新增
     * @author sxmd
     * @param model:
     * @return
     * @date
     */
    String insert${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model);


    /**
     * Description:  ${table.tableComment!''}-批量新增新增
     * @author sxmd
     * @param model:
     * @return
     * @date
     */
    long batchInsert${table.tableNameToJavaName}(List<${table.tableNameToJavaName}AddModel> model);

    /**
     * Description:  ${table.tableComment!''}-更新
     * @author sxmd
     * @param model:
     * @date
     */
    void update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model);

    /**
     * Description:   ${table.tableComment!''}-删除
     * @author sxmd
     * @param id: 主键
     * @date
     */
    void delete${table.tableNameToJavaName}(String id);

    /**
     * Description:   ${table.tableComment!''}-删除
     * @author sxmd
     * @param ids: 主键数组
     * @date
     */
    long batchDelete${table.tableNameToJavaName}(String[] ids);

    /**
     * Description:   ${table.tableComment!''}-详情
     * @author sxmd
     * @param id: 主键
     * @date
     */
    ${table.tableNameToJavaName}DetailsModel get${table.tableNameToJavaName}DetailsById(String id);

    /**
     * Description:   ${table.tableComment!''}-列表
     * @author sxmd
     * @param listRequestModel: 参数
     * @date
     */
    BasePage<${table.tableNameToJavaName}ListResponseModel> findPage${table.tableNameToJavaName}List(RequestPage requestPage,${table.tableNameToJavaName}ListRequestModel listRequestModel);

    /**
     * Description:   ${table.tableComment!''}-所有数据
     * @author sxmd
     * @param :
     * @date
     */
    List<${table.tableNameToJavaName}ListModel> findAll${table.tableNameToJavaName}List();

}
