package com.sxmd.content.${folderName!''}.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxmd.base.BasePage;
import com.sxmd.base.PageBuilder;
import com.sxmd.base.PageStart;
import com.sxmd.base.RequestPage;
import com.sxmd.content.${folderName!''}.mapper.${table.tableNameToJavaName}Mapper;
import com.sxmd.content.${folderName!''}.entity.${table.tableNameToJavaName}Entity;
import com.sxmd.content.${folderName!''}.model.*;
import com.sxmd.util.AssertUtil;
import com.sxmd.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: ${table.tableComment!''} 实现类
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Service
public class ${fileName} extends ServiceImpl<${table.tableNameToJavaName}Mapper,${table.tableNameToJavaName}Entity> implements ${table.tableNameToJavaName}Service {

    /**
     * Description:   新增
     * @author sxmd
     * @param model:
     * @date
     */
    @Override
    public String insert${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model) {
        ${table.tableNameToJavaName}Entity entity = new ${table.tableNameToJavaName}Entity();
        BeanUtils.copyProperties(model, entity);
        baseMapper.insert(entity);
        return entity.getId();
    }

    /**
     * Description:   批量新增
     * @author sxmd
     * @param model:
     * @date
     */
    @Override
    public long batchInsert${table.tableNameToJavaName}(List<${table.tableNameToJavaName}AddModel> model) {
        // 先数据校验，通过后
        List<${table.tableNameToJavaName}Entity> entityList = BeanUtil.copyPropertiesOfList(model,${table.tableNameToJavaName}Entity.class);
        entityList.forEach(x->x.setId(IdWorker.getIdStr()));
        return baseMapper.batchInsert(entityList);
    }

    /**
     * Description: 更新
     * @author sxmd
     * @param model:
     * @date
     */
    @Override
    public void update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model) {
        // 查询当前数据
        ${table.tableNameToJavaName}Entity entity = baseMapper.selectById(model.getId());
        AssertUtil.notNull(entity,"数据不存在不能更新");
        BeanUtils.copyProperties(model, entity);
        baseMapper.updateById(entity);
    }

    /**
     * Description: 删除
     * @author sxmd
     * @param id: 主键
     * @date
     */
    @Override
    public void delete${table.tableNameToJavaName}(String id) {
        ${table.tableNameToJavaName}Entity entity = baseMapper.selectById(id);
        AssertUtil.notNull(entity,"数据不存在不能删除");
        baseMapper.deleteById(id);
    }

    /**
     * Description: 批量删除
     * @author sxmd
     * @param ids: 主键数组
     * @date
     */
    @Override
    public long batchDelete${table.tableNameToJavaName}(String[] ids) {
        return baseMapper.batchDeleteByIds(ids);
    }


    /**
     * Description:   详情
     * @author sxmd
     * @param id: 主键
     * @date
     */
    @Override
    public ${table.tableNameToJavaName}DetailsModel get${table.tableNameToJavaName}DetailsById(String id) {
        ${table.tableNameToJavaName}Entity entity = baseMapper.selectById(id);
        AssertUtil.notNull(entity,"数据不存在");
        ${table.tableNameToJavaName}DetailsModel model = new ${table.tableNameToJavaName}DetailsModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }

    /**
     * Description: 列表
     * @author sxmd
     * @param listRequestModel: 参数
     * @date
     */
    @Override
    public BasePage<${table.tableNameToJavaName}ListResponseModel> findPage${table.tableNameToJavaName}List(RequestPage requestPage, ${table.tableNameToJavaName}ListRequestModel listRequestModel) {
        // 使用page 开始
        PageStart<${table.tableNameToJavaName}ListResponseModel> pageStart = PageBuilder.builderPageStart(requestPage);
        List<${table.tableNameToJavaName}ListResponseModel> list = baseMapper.find${table.tableNameToJavaName}List(pageStart, listRequestModel);
        return PageBuilder.builderResultPage(pageStart, list);
    }

    /**
     * Description: 获得所有数据
     * @author sxmd
     * @param :
     * @date
     */
    @Override
    public List<${table.tableNameToJavaName}ListModel> findAll${table.tableNameToJavaName}List() {
        return baseMapper.findAllList();
    }

}
