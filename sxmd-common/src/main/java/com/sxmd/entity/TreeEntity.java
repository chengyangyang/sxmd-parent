package com.sxmd.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 树形结构   第一个泛型是 数据类型
 *
 * @author cy
 * @date 2020年07月22日 14:25
 * Version 1.0
 */
@Data
public class TreeEntity<T> {

    /**
     * 主键
     */
    private Object id;
    /**
     * 父类id 也可修改成
     */
    private Object parentId;

    private List<T> childList = new ArrayList<>();

}
