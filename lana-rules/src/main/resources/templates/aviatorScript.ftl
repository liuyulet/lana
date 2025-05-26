let m = seq.list();
<#list list as item>
<#-- AND 条件 -->
<#if item.conditionMode == 1>
if (<#list item.conditionList as condition>(deviceId == ${condition.deviceId} && <#if condition.operator == "0">${condition.modeSigns} == ${condition.threshold}</#if><#if condition.operator == "1">${condition.modeSigns} > ${condition.threshold}</#if><#if condition.operator == "2"> ${condition.modeSigns} < ${condition.threshold}</#if>)<#if condition_has_next> && </#if></#list>) {
let hit = seq.map();
seq.put(hit, "actionMap", "${item.actionMap}");
seq.put(hit, "message", "命中条件：<#list item.conditionList as condition><#if condition.operator == "0">${condition.deviceName} 的 ${condition.modeSignsName} == ${condition.threshold}</#if><#if condition.operator == "1">${condition.deviceName} 的 ${condition.modeSignsName} > ${condition.threshold}</#if><#if condition.operator == "2"> ${condition.deviceName} 的 ${condition.modeSignsName} < ${condition.threshold}</#if><#if condition_has_next> 与 </#if></#list>");
seq.add(m, hit);
}
</#if>
<#-- OR 条件 -->
<#if item.conditionMode == 2>
<#list item.conditionList as condition>
if(deviceId == ${condition.deviceId}){
<#if condition.operator == "1">
    if(${condition.modeSigns} > ${condition.threshold}){
    let hit = seq.map();
    seq.put(hit, "actionMap", "${item.actionMap}");
    seq.put(hit, "message", "命中条件：${condition.deviceName} 的 ${condition.modeSignsName} > ${condition.threshold}");
    seq.add(m, hit);
    }
</#if>
<#if condition.operator == "2">
    if(${condition.modeSigns} < ${condition.threshold}){
    let hit = seq.map();
    seq.put(hit, "actionMap", "${item.actionMap}");
    seq.put(hit, "message", "命中条件：${condition.deviceName} 的 ${condition.modeSignsName} < ${condition.threshold}");
    seq.add(m, hit);
    }
</#if>
<#if condition.operator == "0">
    if(${condition.modeSigns} == ${condition.threshold}){
    let hit = seq.map();
    seq.put(hit, "actionMap", "${item.actionMap}");
    seq.put(hit, "message", "命中条件：${condition.deviceName} 的 ${condition.modeSignsName} == ${condition.threshold}");
    seq.add(m, hit);
    }
</#if>
}
</#list>
</#if>
</#list>
return m;
