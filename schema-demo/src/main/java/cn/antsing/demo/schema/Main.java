package cn.antsing.demo.schema;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.start();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String name : beanDefinitionNames){
            System.out.println(name);
        }
//        Class<?> jobDetailTemplate = applicationContext.getType("defaultDateFormat");
//        System.out.println(jobDetailTemplate.getName());
//        SimpleDateFormat defaultDateFormat = (SimpleDateFormat) applicationContext.getBean("defaultDateFormat");
//        System.out.println(defaultDateFormat.format(new Date()));
    }
}
