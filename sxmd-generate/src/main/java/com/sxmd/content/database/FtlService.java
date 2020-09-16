package com.sxmd.content.database;

import com.sxmd.content.database.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Description: 模板对应的配置
 *
 * @author cy
 * @date 2019年06月26日 17:19
 * Version 1.0
 */
@Service
public class FtlService {

    @Autowired
    private GeneratorService generatorService;


    /**
     * Description: zip 需要生成的模板
     *
     * @param tableName:
     * @return void
     * @author cy
     * @date 2019/8/2 16:05
     */
    public void generatorEntity(String tableName) {
        HashMap<String, Object> map = new HashMap<>(10);
        generatorService.generatorJavaFile(true, tableName, "sxmd/AddModel.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/EditModel.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/ListResponseModel.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/ListModel.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/ListRequestModel.ftl", map);
        generatorService.generatorJavaFile(false, tableName, "sxmd/DetailsModel.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/Entity.ftl", map);

        generatorService.generatorJavaFile(true, tableName, "sxmd/Dao.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/Controller.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/Service.ftl", map);
        generatorService.generatorJavaFile(true, tableName, "sxmd/Serviceimpl.ftl", map);
        generatorService.generatorJavaFile(false, tableName, "sxmd/Xml.ftl", map);

    }


}
