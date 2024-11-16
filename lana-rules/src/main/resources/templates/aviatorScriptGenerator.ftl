<#-- aviatorScriptGenerator.ftl -->

use java.util.*;

let results = new ArrayList();

<#if comparison == ">" >
    if (${parmas} &gt; ${threshold}) {
    seq.add(results, "${message}大于${threshold}: " + ${parmas});
    } else {
    seq.add(results, "${message}小于${threshold}: " + ${parmas});
    }
<#elseif comparison == "<">
    if (${parmas} &lt; ${threshold}) {
    seq.add(results, "${message}小于${threshold}: " + ${parmas});
    } else {
    seq.add(results, "${message}小于${threshold}: " + ${parmas});
    }
<#elseif comparison == "=">
    if (${parmas} == ${threshold}) {
    seq.add(results, "${message}等于${threshold}: " + ${parmas});
    } else {
    seq.add(results, "${message}小于${threshold}: " + ${parmas});
    }
</#if>

return results;
