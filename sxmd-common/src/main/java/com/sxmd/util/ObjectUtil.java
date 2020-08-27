package com.sxmd.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:  Object 工具类
 *
 * @author cy
 * @date 2020年06月04日 13:36
 * Version 1.0
 */
public class ObjectUtil {

    private ObjectUtil() {
    }

    public static String objectToString(Object obj) {
        if (obj != null && StringUtils.isNotBlank(obj + "")) {
            return obj + "";
        }
        return null;
    }

    public static String objectToString(Object obj, String nullDefault) {
        if (obj != null && StringUtils.isNotBlank(obj + "")) {
            return obj + "";
        }
        return nullDefault;
    }

}
