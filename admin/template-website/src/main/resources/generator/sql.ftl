<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.modules.${packageModuleName}.dao.${moduleClassName}Dao">

    <resultMap id="baseResultMap" type="${moduleClassName}">
        <#list tableColumnList as tableColumn>
        <#if tableColumn.isPk == "1">
        <id property="${tableColumn.javaField}" column="${tableColumn.name}" />
        </#if>
        <#if tableColumn.isPk == "0">
        <#if tableColumn.jdbcType == "decimal">
        <result property="${tableColumn.javaField}" column="${tableColumn.name}"  jdbcType="DECIMAL" javaType="java.math.BigDecimal"/>
        <#else>
        <result property="${tableColumn.javaField}" column="${tableColumn.name}" />
        </#if>
        </#if>
        </#list>
    </resultMap>

    <sql id="baseColumns">
        <#list tableColumnList as tableColumn>
        <#if tableColumn_has_next>
        a.${tableColumn.name},
        <#else>
        a.${tableColumn.name}
        </#if>
        </#list>
    </sql>

    <insert id="save" parameterType="${moduleClassName}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list tableColumnList as tableColumn>
            <#if tableColumn.jdbcType == "datetime">
            <if test="${tableColumn.javaField} != null">
                ${tableColumn.name},
            </if>
            <#else>
            <if test="${tableColumn.javaField} != null and ${tableColumn.javaField} != ''">
                ${tableColumn.name},
            </if>
            </#if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list tableColumnList as tableColumn>
            <#if tableColumn.jdbcType == "datetime">
            <if test="${tableColumn.javaField} != null">
                <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>,
            </if>
            <#else>
            <if test="${tableColumn.javaField} != null and ${tableColumn.javaField} != ''">
                <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>,
            </if>
            </#if>
        </#list>
        </trim>
    </insert>

    <update id="update" parameterType="${moduleClassName}">
        update ${tableName}
        <set>
            <#list tableColumnList as tableColumn>
            <#if tableColumn.jdbcType == "datetime">
            <if test="${tableColumn.javaField} != null">
                ${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>,
            </if>
            <#else>
            <if test="${tableColumn.javaField} != null and ${tableColumn.javaField} != ''">
                ${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>,
            </if>
            </#if>
            </#list>
        </set>
        <#list tableColumnList as tableColumn>
            <#if tableColumn.isPk == "1">
        where ${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>
            </#if>
        </#list>
    </update>

    <delete id="delete">
        delete from ${tableName}
        <#list tableColumnList as tableColumn>
            <#if tableColumn.isPk == "1">
        where ${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>
            </#if>
        </#list>
    </delete>

    <select id="get" resultMap="baseResultMap">
        select <include refid="baseColumns"/>
        from ${tableName} a
        <#list tableColumnList as tableColumn>
            <#if tableColumn.isPk == "1">
        where a.${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>
            </#if>
        </#list>
    </select>

    <select id="findList" resultMap="baseResultMap">
        select <include refid="baseColumns"/>
        from ${tableName} a
        <where>
        <#list tableColumnList as tableColumn>
            <#if tableColumn.isQuery == "1">
            <#if tableColumn.showType == "3">
            <if test="startTime != null">
                and a.${tableColumn.name} <#noparse>>= #{startTime}</#noparse>
            </if>
            <if test="endTime != null">
                and a.${tableColumn.name} <#noparse>&lt;= #{endTime}</#noparse>
            </if>
            <#else>
            <#-- datatime类型,不能判断为空字符串 -->
            <#if tableColumn.jdbcType == "datetime">
            <if test="${tableColumn.javaField} != null">
                <#-- queryType=0为等于 -->
                <#if tableColumn.queryType == '0'>
                and a.${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>
                </#if>
                <#-- queryType=1为like -->
                <#if tableColumn.queryType == '1'>
                and a.${tableColumn.name} like concat('%', <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>, '%')
                </#if>
            </if>
            <#else>
            <#-- datatime类型,不能判断为空字符串 -->
            <if test="${tableColumn.javaField} != null and ${tableColumn.javaField} != ''">
                <#if tableColumn.queryType == '0'>
                and a.${tableColumn.name} = <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>
                </#if>
                <#if tableColumn.queryType == '1'>
                and a.${tableColumn.name} like concat('%', <#noparse>#{</#noparse>${tableColumn.javaField}<#noparse>}</#noparse>, '%')
                </#if>
            </if>
            </#if>
            </#if>
            </#if>
        </#list>
        </where>
        order by create_date desc
    </select>
</mapper>