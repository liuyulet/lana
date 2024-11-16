package com.lana.base.syshandle.xss.proties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/7 20:54
 */
public class JsoupXssCleaner implements XssCleaner {

    private final Safelist safelist;


    private final String baseUri;


    public JsoupXssCleaner() {
        this.safelist = buildSafelist();
        this.baseUri = "";
    }


    private Safelist buildSafelist() {
        // 使用 jsoup 提供的默认的
        Safelist relaxedSafelist = Safelist.relaxed();
        // 富文本编辑时一些样式是使用 style 来进行实现的
        // 比如红色字体 style="color:red;", 所以需要给所有标签添加 style 属性
        // 注意：style 属性会有注入风险 <img STYLE="background-image:url(javascript:alert('XSS'))">
        relaxedSafelist.addAttributes(":all", "style", "class");
        // 保留 a 标签的 target 属性
        relaxedSafelist.addAttributes("a", "target");
        // 支持img 为base64
        relaxedSafelist.addProtocols("img", "src", "data");


        return relaxedSafelist;
    }

    @Override
    public String clean(String html) {
        return Jsoup.clean(html, baseUri, safelist, new Document.OutputSettings().prettyPrint(false));
    }

}
