package cn.antsing.rpc.schema;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Producer bean定义解析器
 *  <syq:producer interfac="cn.antsing.service.IUserService" ref="userService" />
 */
public class ProducerBeanDefParser extends AbstractBeanDefinitionParser {
    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Producer.class);
        builder.addPropertyValue("interfac",element.getAttribute("interfac"));
        builder.addPropertyValue("ref",element.getAttribute("ref"));
        return builder.getBeanDefinition();
    }
}
