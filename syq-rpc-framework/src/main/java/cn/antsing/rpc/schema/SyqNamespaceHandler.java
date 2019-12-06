package cn.antsing.rpc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Syq 命名标签处理器
 */
public class SyqNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {

        registerBeanDefinitionParser("producer",new ProducerBeanDefParser());
        registerBeanDefinitionParser("consumer",new ConsumerBeanDefParser());
    }
}
