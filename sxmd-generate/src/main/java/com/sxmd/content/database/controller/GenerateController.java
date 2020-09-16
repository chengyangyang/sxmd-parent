package com.sxmd.content.database.controller;

import com.sxmd.content.database.FtlService;
import com.sxmd.content.database.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Description: 数据请求
 *
 * @author cy
 * @date 2019年06月26日 11:12
 * Version 1.0
 */
@Controller
@Slf4j
@Api(value = "代码生成 controller", tags = "代码生成")
public class GenerateController {

    @Autowired
    private GeneratorService service;
    @Autowired
    private FtlService ftlService;

    /**
     * Description:   查询所有数据库表
     *
     * @param :
     * @return org.springframework.web.servlet.ModelAndView
     * @author cy
     * @date 2019/7/1 18:05
     */
    @ApiOperation(value = "所有表", notes = "所有表")
    @GetMapping("/generate/list")
    private ModelAndView home(String inputTableName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("generatelist");
        mv.addObject("inputTableName", inputTableName);
        mv.addObject("data", service.getTableAll(inputTableName));
        return mv;
    }


    /**
     * Description:   直接生成至项目目录
     *
     * @param tableNameValue: 表名称
     * @param :
     * @return void
     * @author cy
     * @date 2019/8/2 9:08
     */
    @ApiOperation(value = "生成代码", notes = "生成代码至项目中")
    @GetMapping("/generate")
    public ModelAndView generateToProject(@RequestParam("tableNameValue") String tableNameValue) throws IOException {
        // 根据表名称直接生成至项目中
        ftlService.generatorEntity(tableNameValue);
        log.debug("完成下载");
        ModelAndView mv = new ModelAndView();
        mv.addObject("inputTableName", null);
        mv.setViewName("redirect:/generate/list");
        return mv;
    }

}
