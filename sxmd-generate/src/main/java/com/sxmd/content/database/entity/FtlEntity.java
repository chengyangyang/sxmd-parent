package com.sxmd.content.database.entity;

import lombok.Data;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 17:22
 * Version 1.0
 */
@Data
public class FtlEntity {

    /**
     * 文件夹路径
     */
    private String createFilePath;
    /**
     * 文件名称{0} 表示 用表转化后的名称进行替换
     */
    private String createFileName = "{0}.java";

    public FtlEntity(String createFilePath, String createFileName) {
        this.createFilePath = createFilePath;
        this.createFileName = createFileName;
    }

}
