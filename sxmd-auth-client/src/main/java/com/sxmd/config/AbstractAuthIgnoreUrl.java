package com.sxmd.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 默认的url 忽略
 *
 * @author cy
 * @date 2020年09月15日 10:42
 * Version 1.0
 */
public abstract class AbstractAuthIgnoreUrl implements AuthIgnoreUrl {

    @Override
    public List<String> ignoreUrl() {
        List<String> list = new ArrayList<>();
        // swagger
        list.add("/swagger-ui/**");
        list.add("/swagger-resources/**");
        list.add("/v2/api-docs");
        // knife4j
        list.add("/doc.html");
        list.add("/webjars/**");
        return list;
    }
}
