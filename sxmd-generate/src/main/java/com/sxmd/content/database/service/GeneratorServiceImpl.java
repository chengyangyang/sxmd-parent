package com.sxmd.content.database.service;

import cn.hutool.core.util.StrUtil;
import com.sxmd.config.FreemarkerConfig;
import com.sxmd.config.FtlConfig;
import com.sxmd.content.database.entity.ColumnEntity;
import com.sxmd.content.database.entity.FtlEntity;
import com.sxmd.content.database.entity.TableEntity;
import com.sxmd.content.database.mapper.MysqlGeneratorMapper;
import com.sxmd.content.database.mapper.PostgreGeneratorMapper;
import com.sxmd.help.SqlToJavaHelp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 17:05
 * Version 1.0
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Value("${sxmd.basedate:}")
    private String database;
    @Value("${sxmd.generate-path:}")
    private String generatePath;

    @Resource
    private MysqlGeneratorMapper mysqlDao;
    @Resource
    private PostgreGeneratorMapper postgreDao;

    private static final String DATABASE_POSTGRESQL = "postgresql";
    private static final String DATABASE_MYSQL = "mysql";

    /**
     * Description:   查询列表
     *
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.TableEntity>
     * @author cy
     * @date 2019/6/26 17:14
     */
    @Override
    public List<TableEntity> getTableAll(String tableName) {
        if (DATABASE_POSTGRESQL.equals(database)) {
            return postgreDao.getAllTable(tableName);
        } else if (DATABASE_MYSQL.equals(database)) {
            return mysqlDao.getAllTable(tableName);
        } else {
            return postgreDao.getAllTable(tableName);
        }
    }


    /**
     * Description:   对实体进行 相应的转化
     *
     * @param tableName:
     * @return com.sxmd.content.database.entity.TableEntity
     * @author cy
     * @date 2019/6/26 16:50
     */
    @Override
    public TableEntity getTableByTableNameAndInit(String tableName) {
        TableEntity oneTable = getOneTable(tableName);
        oneTable.setTableNameToJavaName(SqlToJavaHelp.classNameByTable(oneTable.getTableName()));
        return oneTable;
    }

    /**
     * Description:   对列 进行相应的处理
     *
     * @param isFilterColumns:
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.ColumnEntity>
     * @author cy
     * @date 2019/6/26 16:59
     */
    @Override
    public List<ColumnEntity> getColumnsByTableInit(boolean isFilterColumns, String tableName) {
        List<ColumnEntity> columnsByTable = getColumnsByTable(tableName);
        columnsByTable.forEach(x -> {
            x.setColumnNameToJava(StrUtil.toCamelCase(x.getColumnName()));
            x.setColumnTypeToJava(SqlToJavaHelp.getJavaTypeBySqlType(x.getColumnType()));
            x.setImportPackageNumer(SqlToJavaHelp.IMPORT_PACKAGE.get(x.getColumnTypeToJava()));
        });
        if (isFilterColumns) {
            // 对集合进行过滤
            return columnsByTable.stream().filter(x -> !SqlToJavaHelp.filterSqlColumns(x.getColumnName())).collect(Collectors.toList());
        } else {
            return columnsByTable;
        }
    }

    /**
     * Description:   文件生成
     *
     * @param isFilterColumns: 是否进行数据过滤
     * @param tableName:       是否使用表名称
     * @param templateName:    模板名称
     * @param map:             数据
     * @return void
     * @author cy
     * @date 2019/6/26 17:09
     */
    @Override
    public void generatorJavaFile(boolean isFilterColumns, String tableName, String templateName, Map<String, Object> map) {
        String path = System.getProperty("user.dir") + File.separator + "src/main/java/com/sxmd/content/";
        if (StringUtils.isNotBlank(generatePath)) {
            path = generatePath;
        }
        // 根据表名称获取文件夹名称
        String mkdirName = SqlToJavaHelp.getMkdirName(tableName);
        FtlEntity ftlEntity = FtlConfig.getFtlEntity(templateName);
        String filePath = ftlEntity.getCreateFilePath();
        // 文件名称
        filePath = path + mkdirName + filePath;
        // 替换文件夹名称
        String fileName = ftlEntity.getCreateFileName();
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new RuntimeException("文件路径只能为文件夹");
            }
        } else {
            if (!file.mkdirs()) {
                throw new RuntimeException("文件夹创建失败");
            }
        }
        // 如果输入的表名称不是空的，把表的字段进入
        if (StringUtils.isNotBlank(tableName)) {
            TableEntity table = this.getTableByTableNameAndInit(tableName);
            List<ColumnEntity> columns = this.getColumnsByTableInit(isFilterColumns, tableName);
            map.put("table", table);
            map.put("columns", columns);
            // 对文件的包进行处理
            Set<String> strings = new HashSet<>();
            columns.forEach(x -> {
                strings.add(x.getImportPackageNumer());
            });
            map.put("importPackageNumer", strings);
            // 对文件进行处理（替换标识符的内容）
            fileName = MessageFormat.format(ftlEntity.getCreateFileName(), table.getTableNameToJavaName());
            map.put("fileName", fileName.substring(0, fileName.lastIndexOf('.')));
        }
        map.put("folderName", mkdirName);
        map.put("urlName", SqlToJavaHelp.urlNameByTable(tableName));
        FreemarkerConfig.generatorFile(templateName, filePath + File.separator + fileName, map);
    }

    /**
     * Description:   获得单个表
     *
     * @param tableName:
     * @return com.sxmd.content.database.entity.TableEntity
     * @author cy
     * @date 2019/6/26 16:51
     */
    private TableEntity getOneTable(String tableName) {
        if (DATABASE_POSTGRESQL.equals(database)) {
            return postgreDao.getOneTable(tableName);
        } else if (DATABASE_MYSQL.equals(database)) {
            return mysqlDao.getOneTable(tableName);
        } else {
            return postgreDao.getOneTable(tableName);
        }
    }

    /**
     * Description:   得到数据库的字段列
     *
     * @param tableName:
     * @return java.util.List<com.sxmd.content.database.entity.ColumnEntity>
     * @author cy
     * @date 2019/7/1 18:00
     */
    private List<ColumnEntity> getColumnsByTable(String tableName) {
        if (DATABASE_POSTGRESQL.equals(database)) {
            return postgreDao.getColumnsByTable(tableName);
        } else if (DATABASE_MYSQL.equals(database)) {
            return mysqlDao.getColumnsByTable(tableName);
        } else {
            return postgreDao.getColumnsByTable(tableName);
        }
    }
}
