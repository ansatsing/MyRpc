package cn.antsing.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Consumer 命名标签处理器
 */
public class ProducerNamespaceHandler  extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("consumer",new ConsumerBeanDefParser());
    }
}
