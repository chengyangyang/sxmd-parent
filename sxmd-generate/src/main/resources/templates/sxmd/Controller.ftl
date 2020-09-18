package com.sxmd.content.${folderName!''}.controller;

import com.sxmd.base.BaseController;
import com.sxmd.base.BasePage;
import com.sxmd.base.JsonResult;
import com.sxmd.base.RequestPage;
import com.sxmd.constant.ConstantWeb;
import com.sxmd.content.${folderName!''}.model.*;
import com.sxmd.content.${folderName!''}.service.${table.tableNameToJavaName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Description: ${table.tableComment!''} 控制器
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@RestController
@Api(value = "${table.tableComment!''} controller",tags = "${table.tableComment!''}")
@RequestMapping("${urlName!''}")
public class ${fileName} extends BaseController {

    @Autowired
    private ${table.tableNameToJavaName}Service service;


    /**
     * Description:   ${table.tableComment!''}-列表
     * @author sxmd
     * @param listRequestModel: 参数
     * @date
     */
    @ApiOperation(value = "分页列表", notes = "${table.tableComment!''}")
    @GetMapping
    public JsonResult<BasePage<${table.tableNameToJavaName}ListResponseModel>> page(RequestPage requestPage, ${table.tableNameToJavaName}ListRequestModel listRequestModel) {
        return success(service.findPage${table.tableNameToJavaName}List(requestPage,listRequestModel));
    }

    /**
     * Description:   ${table.tableComment!''}-所有数据
     * @author sxmd
     * @param :
     * @date
     */
    @ApiOperation(value = "所有数据", notes = "获得所有${table.tableComment!''}数据")
    @GetMapping("/all")
    public JsonResult<List<${table.tableNameToJavaName}ListModel>> all() {
        return success(service.findAll${table.tableNameToJavaName}List());
    }


    /**
     * Description:   ${table.tableComment!''}-详情
     * @author sxmd
     * @param id: 主键
     * @date
     */
    @ApiOperation(value = "详情", notes = "根据${table.tableComment!''}ID获取单条${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "String", paramType = "path")
    @GetMapping("{id}")
    public JsonResult<${table.tableNameToJavaName}DetailsModel> get(@PathVariable("id") String id) {
        return success(service.get${table.tableNameToJavaName}DetailsById(id));
    }


    /**
     * Description:   ${table.tableComment!''}-新增
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "新增", notes = "新增${table.tableComment!''}")
    @PostMapping
    public JsonResult<String> insert(@RequestBody @Valid ${table.tableNameToJavaName}AddModel model) {
        service.insert${table.tableNameToJavaName}(model);
        return success(ConstantWeb.SAVE_MSEEAGE);
    }

    /**
     * Description:   ${table.tableComment!''}-批量新增
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "批量新增", notes = "批量新增${table.tableComment!''}")
    @PostMapping("batch/insert")
    public JsonResult<String> batchInsert(@RequestBody @Valid List<${table.tableNameToJavaName}AddModel> model) {
        long result = service.batchInsert${table.tableNameToJavaName}(model);
        return success("批量新增" + result + "条记录");
    }


    /**
     * Description:   ${table.tableComment!''}-修改
     * @author sxmd
     * @param model:
     * @date
     */
    @ApiOperation(value = "修改", notes = "根据id更新${table.tableComment!''}")
    @PutMapping
    public JsonResult<String> update(@RequestBody @Valid ${table.tableNameToJavaName}EditModel model) {
        service.update${table.tableNameToJavaName}(model);
        return success(ConstantWeb.UPDATE_MSEEAGE);
    }


    /**
     * Description:   ${table.tableComment!''}-删除
     * @author sxmd
     * @param id:
     * @date
     */
    @ApiOperation(value = "删除", notes = "根据Id删除${table.tableComment!''}")
    @ApiImplicitParam(name = "id", value = "主键 id", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("{id}")
    public JsonResult<String> delete(@PathVariable("id") String id) {
        service.delete${table.tableNameToJavaName}(id);
        return success(ConstantWeb.DELETE_MSEEAGE);
    }


    /**
     * Description:   ${table.tableComment!''}-批量删除
     * @author sxmd
     * @param ids:
     * @date
     */
    @ApiOperation(value = "批量删除", notes = "根据Id数组删除${table.tableComment!''}")
    @DeleteMapping("ids")
    public JsonResult<String> batchDelete(@RequestBody String[] ids) {
        long result = service.batchDelete${table.tableNameToJavaName}(ids);
        return success("批量删除" + result + "条记录");
    }

 }
