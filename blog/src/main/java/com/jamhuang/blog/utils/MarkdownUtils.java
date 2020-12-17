package com.jamhuang.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {

    /**
     * markdown 格式转换成 HTML 格式
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * markdown 格式转换成 HTML 格式
     * 增加扩展 - 标题描点，表格生成
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown) {
        // h 标题生成 id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        // 转换 table 的 HTML
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(extensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {

        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if (node instanceof Link) {
                map.put("target","_blank");
            }
            if (node instanceof TableBlock) {
                map.put("class","ui celled table");
            }
        }
    }
}
