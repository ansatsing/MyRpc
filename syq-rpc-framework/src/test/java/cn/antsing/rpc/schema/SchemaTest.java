package cn.antsing.rpc.schema;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SchemaTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("SchemaTest.xml");
        applicationContext.start();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        Assert.assertTrue("syq标签解析失败",beanDefinitionNames.length == 2);
    }
}
