<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.sxmd.content.${folderName!''}.mapper.${table.tableNameToJavaName}Mapper">

    <sql id="base_column">
         <#list columns as li>
             a.${li.columnName} as ${li.columnNameToJava}<#if li_has_next>,</#if>
         </#list>
    </sql>


    <select id="find${table.tableNameToJavaName}List" resultType="com.sxmd.content.${folderName!''}.model.${table.tableNameToJavaName}ListResponseModel">
        select
        <include refid="base_column"></include>
        from ${table.tableName} as a
    </select>

    <select id="findAllList" resultType="com.sxmd.content.${folderName!''}.model.${table.tableNameToJavaName}ListModel">
        select
        <include refid="base_column"></include>
        from ${table.tableName} as a
    </select>


    <insert id="batchInsert">
       insert ignore into ${table.tableName}
       (
        <#list columns as li>
            ${li.columnName}<#if li_has_next>,</#if>
        </#list>
        )
       values
        <foreach collection="list" separator="," item="item">
            (
            <#list columns as li>
                <#noparse>#{</#noparse>item.${li.columnNameToJava}<#noparse>}</#noparse><#if li_has_next>,</#if>
            </#list>
            )
        </foreach>
    </insert>

    <delete id="batchDeleteByIds">
        delete from ${table.tableName}
        where id in
        <foreach collection="ids" item="id" open="(" separator="," close=")">
            <#noparse>#{</#noparse>id<#noparse>}</#noparse>
        </foreach>
    </delete>

</mapper>