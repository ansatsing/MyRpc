package cn.antsing.demo.schema.nesting.namespacehandler;

import cn.antsing.demo.schema.nesting.namespacehandler.beanparser.ComponentBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * xml命名处理器
 */
public class ComponentNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("component",new ComponentBeanDefinitionParser());
    }
}
