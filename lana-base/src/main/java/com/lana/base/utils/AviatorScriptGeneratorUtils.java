package com.lana.base.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * @author liuyulet
 * @create 2025/3/5 13:09
 */public class AviatorScriptGeneratorUtils {

    public static String getAviatorScriptGenerator(Map templateData){
        try {
            // 配置 FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setClassForTemplateLoading(AviatorScriptGeneratorUtils.class, "/templates");
            // 加载 FreeMarker 模板
            Template template = cfg.getTemplate("aviatorScript.ftl");
            // 使用模板生成代码
            StringWriter writer = new StringWriter();
            template.process(templateData, writer);
            // 输出生成的 AviatorScript 代码
            String aviatorScriptCode = writer.toString();
            return aviatorScriptCode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
