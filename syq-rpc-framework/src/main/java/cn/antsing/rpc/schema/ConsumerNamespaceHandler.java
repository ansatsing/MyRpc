package cn.antsing.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Producer 命名标签处理器
 */
public class ConsumerNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("producer",new ProducerBeanDefParser());
    }
}
