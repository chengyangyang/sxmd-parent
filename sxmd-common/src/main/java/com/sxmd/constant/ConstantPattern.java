package com.sxmd.constant;

/**
 * Description:  Pattern 常量
 *
 * @author cy
 * @date 2020年07月03日 11:23
 * Version 1.0
 */
public final class ConstantPattern {

    private ConstantPattern() {
    }

    /**
     * 正则表达式
     *
     */

    /**
     * 组成 数字 字母 下划线
     */
    public static final String REGEX_ALPHANUMERIC_UNDERLINE = "^[0-9a-zA-Z_]+$";

    /**
     * 数字校验,包含小数点
     */
    public static final String REGEX_NUMBER = "(\\s)*([+-])?(([0-9]*\\.)?([0-9]+)|([0-9]+)(\\.[0-9]*)?)([eE][\\+-]?[0-9]+)?(\\s)*";


    /**
     * 时间
     */
    public static final String DATE_PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_DATE = "yyyy-MM-dd";
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";

}
