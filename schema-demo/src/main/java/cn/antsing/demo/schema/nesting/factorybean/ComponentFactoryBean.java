package cn.antsing.demo.schema.nesting.factorybean;

import cn.antsing.demo.schema.nesting.bean.Component;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * component的工厂bean
 */
public class ComponentFactoryBean implements FactoryBean<Component> {
    private Component parent;
    private List<Component> children;

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public void setChildren(List<Component> children) {
        this.children = children;
    }

    @Override
    public Component getObject() throws Exception {
        if(this.children != null && !this.children.isEmpty() && this.parent != null){
            for(Component child : this.children){
                this.parent.addComponent(child);
            }
        }
        return this.parent;
    }

    @Override
    public Class<?> getObjectType() {
        return Component.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
