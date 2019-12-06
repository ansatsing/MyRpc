package cn.antsing.demo.schema.nesting.namespacehandler.beanparser;

import cn.antsing.demo.schema.nesting.bean.Component;
import cn.antsing.demo.schema.nesting.factorybean.ComponentFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * bean定义解析器
 */
public class ComponentBeanDefinitionParser extends AbstractBeanDefinitionParser {
    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        return parseComponentElement(element);
    }

    private AbstractBeanDefinition parseComponentElement(Element element) {
        BeanDefinitionBuilder componentBeanDefBuilder = BeanDefinitionBuilder.rootBeanDefinition(ComponentFactoryBean.class);
        componentBeanDefBuilder.addPropertyValue("parent",parseComponent(element));
        List<Element> elements = DomUtils.getChildElementsByTagName(element, "component");
        if(elements!=null && !elements.isEmpty()){
            parseChildComponent(elements,componentBeanDefBuilder);
        }
        return componentBeanDefBuilder.getBeanDefinition();
    }

    private void parseChildComponent(List<Element> elements, BeanDefinitionBuilder componentBeanDefBuilder) {
        //List<BeanDefinition> children = new ArrayList<>();
        ManagedList<BeanDefinition> children = new ManagedList<>(elements.size());
        for(Element element : elements){
            children.add(parseComponentElement(element));
        }
        componentBeanDefBuilder.addPropertyValue("children",children);
    }

    private BeanDefinition parseComponent(Element element) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Component.class);
        beanDefinitionBuilder.addPropertyValue("name",element.getAttribute("name"));
        return  beanDefinitionBuilder.getBeanDefinition();
    }
}
