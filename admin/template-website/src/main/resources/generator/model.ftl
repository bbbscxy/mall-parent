package ${packageName}.modules.${packageModuleName}.model;

import com.vim.common.base.DataEntity;

<#list tableColumnList as tableColumn>
<#if tableColumn.isQuery == "1" && tableColumn.showType == "3">
import java.util.Date;
</#if>
</#list>

/**
* @作者 Administrator
* @时间 ${commentTime}
* @版本 1.0
* @说明
*/
public class ${moduleClassName} extends DataEntity {

<#list tableColumnList as tableColumn>
<#if tableColumn.javaField != 'id' && tableColumn.javaField != 'createBy' && tableColumn.javaField != 'createDate' && tableColumn.javaField != 'updateBy' && tableColumn.javaField != 'updateDate' && tableColumn.javaField != 'remarks' && tableColumn.javaField != 'delFlag'>
    /**
    * ${tableColumn.comments}
    */
    private String ${tableColumn.javaField};
</#if>
</#list>

<#list tableColumnList as tableColumn>
<#if tableColumn.isQuery == "1" && tableColumn.showType == "3">
    private Date startTime;
    private Date endTime;
</#if>
</#list>

<#list tableColumnList as tableColumn>
<#if tableColumn.javaField != 'id' && tableColumn.javaField != 'createBy' && tableColumn.javaField != 'createDate' && tableColumn.javaField != 'updateBy' && tableColumn.javaField != 'updateDate' && tableColumn.javaField != 'remarks' && tableColumn.javaField != 'delFlag'>
    public String get${tableColumn.javaFieldUpper}() {
        return ${tableColumn.javaField};
    }

    public void set${tableColumn.javaFieldUpper}(String ${tableColumn.javaField}) {
        this.${tableColumn.javaField} = ${tableColumn.javaField};
    }

</#if>
</#list>

<#list tableColumnList as tableColumn>
    <#if tableColumn.isQuery == "1" && tableColumn.showType == "3">
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    </#if>
</#list>

}