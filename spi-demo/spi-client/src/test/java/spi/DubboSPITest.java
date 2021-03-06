package spi;

import cn.antsing.spi.api.Robot;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.regex.Pattern;

public class DubboSPITest {
    @Test
    public void sayHello(){
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot robot1= extensionLoader.getExtension("bumblebee");
        robot1.sayHello();
        Robot robot2= extensionLoader.getExtension("optimusPrime");
        robot2.sayHello();
    }

    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,;]+\\s*");
    @Test
    public void testPattern(){
        String str="aaa;,nnnn";
        String[] split = NAME_SEPARATOR.split(str);
        for(String s:split){
            System.out.println(s);
        }
    }
}
