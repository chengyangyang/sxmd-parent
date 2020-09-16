package com.sxmd.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;

/**
 * Description: 模板配置
 *
 * @author cy
 * @date 2019年06月26日 13:18
 * Version 1.0
 */
@Slf4j
public class FreemarkerConfig {

    private static Configuration cfg;


    public static Configuration getConfiguration() {
        if (null == cfg) {
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(FreemarkerConfig.class, "/templates/");
        }
        return cfg;
    }

    /**
     * Description: 模板的生成
     *
     * @param templateName: 模板名称
     * @param filePath:     文件生成路径
     * @param map:          数据封装
     * @return void
     * @author cy
     * @date 2019/6/26 17:07
     */
    public static void generatorFile(String templateName, String filePath, Map<String, Object> map) {
        Template template = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                log.warn("该文件已存在[{}],不能生成。", filePath);
                return;
            }
            template = getConfiguration().getTemplate(templateName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            template.process(map, out);
            log.debug("文件生成路径---->" + filePath);
            out.close();
        } catch (IOException | TemplateException e) {
            log.warn("context", e);
        }
    }

}
