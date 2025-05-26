<#list list as item>
    <#list item.conditionList as condition>
        if(deviceId == ${condition.deviceId}){
        <#if condition.operator == "1">
            if(${condition.modeSigns} > ${condition.threshold}){
            return ${item.actionMap};
            }
        </#if>
        <#if condition.operator == "2">
            if(${condition.modeSigns} < ${condition.threshold}){
            return ${item.actionMap};
            }
        </#if>
        <#if condition.operator == "0">
            if(${condition.modeSigns} == ${condition.threshold}){
            return ${item.actionMap};
            }
        </#if>
        }
    </#list>
</#list>
