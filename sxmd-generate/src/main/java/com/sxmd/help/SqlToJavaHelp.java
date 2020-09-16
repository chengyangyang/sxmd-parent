package com.sxmd.help;

import cn.hutool.core.util.StrUtil;
import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: sql 转化java
 *
 * @author cy
 * @date 2019年06月26日 15:59
 * Version 1.0
 */
@Slf4j
public class SqlToJavaHelp {

    private static Map map = new HashMap<String, String>();

    private static List list = new ArrayList<String>();

    private static String TABLE_NAME_TO_JAVA_NAME_STRATEGY = "0";

    /**
     * 导入对应的包
     */
    public static Map<String, String> IMPORT_PACKAGE = new HashMap<String, String>() {
        private static final long serialVersionUID = 3629368217228539153L;

        {
            put("LocalDateTime", "import java.time.LocalDateTime;");
            put("BigDecimal", "import java.math.BigDecimal;");
            put("LocalDate", "import java.time.LocalDate;");
        }
    };


    public static void initMapAndListData(Map mapData, List listData, String strategy) {
        map.putAll(mapData);
        list.addAll(listData);
        TABLE_NAME_TO_JAVA_NAME_STRATEGY = strategy;
    }


    /**
     * Description:   实体列的类型转化
     *
     * @param sqlType:
     * @return java.lang.String
     * @author cy
     * @date 2019/6/26 17:02
     */
    public static String getJavaTypeBySqlType(String sqlType) {
        String str = (String) map.get(sqlType);
        if (StringUtils.isBlank(str)) {
            str = sqlType;
            log.error("没有找到对应的Java类型！" + sqlType);
        }
        return str;
    }

    /**
     * Description:   对该字段是否进行过滤
     *
     * @param sqlColumnsName:
     * @return boolean
     * @author cy
     * @date 2019/6/27 9:04
     */
    public static boolean filterSqlColumns(String sqlColumnsName) {
        return list.contains(sqlColumnsName);
    }


    public static String tableNameFormat(String tableName) {
        // 截取第一个下划线前面的部分
        String strategy = "0";  // 原文件
        String strategy1 = "1"; // 去掉第一个_前端
        if (strategy.equals(TABLE_NAME_TO_JAVA_NAME_STRATEGY)) {

        } else if (strategy1.equals(TABLE_NAME_TO_JAVA_NAME_STRATEGY)) {
            String indexStr = "_";
            if (tableName.contains(indexStr)) {
                tableName = tableName.substring(tableName.indexOf(indexStr), tableName.length());
            }
        } else {
            tableName = TABLE_NAME_TO_JAVA_NAME_STRATEGY;
        }
        return tableName;
    }

    /**
     * Description:   得到类的名称
     *
     * @param tableName:
     * @return java.lang.String
     * @author cy
     * @date 2019/6/26 16:56
     */
    public static String classNameByTable(String tableName) {
        tableName = tableNameFormat(tableName);
        // 驼峰式转化 并首字母大写
        String capitalize = StringUtil.capitalize(tableName);
        return StrUtil.toCamelCase(capitalize);
    }

    /**
     * Description:   得到url的名称
     *
     * @param tableName:
     * @return java.lang.String
     * @author cy
     * @date 2019/6/26 16:56
     */
    public static String urlNameByTable(String tableName) {
        tableName = tableNameFormat(tableName);
        String urlName = tableName.replaceAll("_", "-").toLowerCase();
        if (urlName.startsWith("-")) {
            urlName = urlName.substring(1, urlName.length());
        }
        return urlName;
    }


    /**
     * Description:  根据表名称得到文件夹名称
     *
     * @param tableName:
     * @return java.lang.String
     * @author cy
     * @date 2019/6/26 16:56
     */
    public static String getMkdirName(String tableName) {
        String s = classNameByTable(tableName);
        return s.toLowerCase();
    }

}
