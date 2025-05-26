package com.lana.base.syshandle.xss;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:14
 */
public class XssUtils {
    /**
     * 不允许的标签
     * <script>
     * <iframe>
     * <embed>
     * <object>
     * <applet>
     * <form>
     * <input>
     * <textarea>
     * <button>
     * <select>
     * <option>
     * <style>
     * <link>
     * <meta>
     * <base>
     * <frame>
     * <frameset>
     * <noframes>
     * <iframe>
     * <video>
     * <audio>
     * <canvas>
     * <svg>
     * <math>
     * 不允许的属性
     * onload
     * onclick
     * onerror
     * onmouseover
     * onfocus
     * onblur
     * style
     * srcdoc
     * 不允许的协议
     * javascript:
     * data:
     */
    static Safelist customSafelist = new Safelist()
            .addTags("a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt",
                    "em", "h1", "h2", "h3", "h4", "h5", "h6", "i", "li", "ol", "p",
                    "pre", "q", "small", "span", "strike", "strong", "sub", "sup",
                    "u", "ul")
            .addAttributes("a", "href", "title", "target")
            .addAttributes("blockquote", "cite")
            .addAttributes("q", "cite")
            .addAttributes("img", "src", "alt", "title", "width", "height")
            .addProtocols("a", "href", "http", "https", "mailto")
            .addProtocols("blockquote", "cite", "http", "https")
            .addProtocols("q", "cite", "http", "https")
            .addProtocols("img", "src", "http", "https");
    /**
     * 不格式化
     */
    private final static Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    public static String filter(String content) {
        return Jsoup.clean(content, "", customSafelist, outputSettings);
    }
}
