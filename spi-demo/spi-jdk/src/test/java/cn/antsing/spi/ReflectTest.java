package cn.antsing.spi;

import cn.antsing.spi.api.Robot;
import com.alibaba.dubbo.common.extension.SPI;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectTest {
    @Test
    public void testMethodGetParameterTypes() {
        Robot robot = new Bumblebee();
        for (Method method : robot.getClass().getMethods()) {
            System.out.println("Method name:"+method.getName());
            for (Class<?> clazz : method.getParameterTypes()){
                System.out.print(clazz.getSimpleName()+"\t");
            }
            System.out.println("===================================");
        }
    }
    @Test
    public void testIsAnnotationPresent(){
        Class c = Bumblebee.class;
        if(c.isAnnotationPresent(SPI.class)){
            System.out.println("YES");
        }
    }
}
