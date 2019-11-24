package spi;

import cn.antsing.spi.api.Robot;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ServiceLoader;

public class JdkSpiTest {
    /**
     *
     */
    @Test
    public void sayHello(){
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

    @Test
    public void testNumOfServiceFile() throws IOException {
        String fileName = "META-INF/services/cn.antsing.spi.api.Robot";
        Enumeration<URL> systemResources = ClassLoader.getSystemResources(fileName);
        while (systemResources.hasMoreElements()){
            System.out.println(systemResources.nextElement().toString());
        }

    }
}
