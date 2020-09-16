package com.sxmd.config;

import com.sxmd.content.database.entity.FtlEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 每个ftl 文件都对应一个配置
 *
 * @author cy
 * @date 2019年06月26日 17:21
 * Version 1.0
 */
public class FtlConfig {

    private static Map map = new HashMap<String, FtlEntity>();


    /**
     * Description:   项目启动后初始化数据 这里面文件下载后会进行删除
     *
     * @return void
     * @author cy
     * @date 2019/7/31 19:39
     */
    public static void initZipDate() {
        map.put("sxmd/Entity.ftl", new FtlEntity(File.separator + "entity", "{0}Entity.java"));
        map.put("sxmd/AddModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}AddModel.java"));
        map.put("sxmd/EditModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}EditModel.java"));
        map.put("sxmd/ListRequestModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}ListRequestModel.java"));
        map.put("sxmd/ListResponseModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}ListResponseModel.java"));
        map.put("sxmd/ListModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}ListModel.java"));
        map.put("sxmd/DetailsModel.ftl", new FtlEntity(File.separator + "model" + File.separator, "{0}DetailsModel.java"));

        map.put("sxmd/Dao.ftl", new FtlEntity(File.separator + "mapper", "{0}Mapper.java"));
        map.put("sxmd/Controller.ftl", new FtlEntity(File.separator + "controller", "{0}Controller.java"));
        map.put("sxmd/Service.ftl", new FtlEntity(File.separator + "service", "{0}Service.java"));
        map.put("sxmd/Serviceimpl.ftl", new FtlEntity(File.separator + "service", "{0}ServiceImpl.java"));
        map.put("sxmd/Xml.ftl", new FtlEntity(File.separator + "mapper", "{0}Mapper.xml"));
    }

    public static FtlEntity getFtlEntity(String templateName) {
        Object obj = map.get(templateName);
        if (Objects.isNull(obj)) {
            throw new RuntimeException("没有对应的模板");
        }
        return (FtlEntity) obj;
    }


}
