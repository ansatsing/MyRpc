package cn.antsing.demo.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MynsNamespaceHandler extends NamespaceHandlerSupport {


    public void init() {
        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
    }
}
