import cn.antsing.spi.api.IService;
import cn.antsing.spi.api.Robot;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DubboSPITest {
    @Test
    public void sayHello() {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot robot1 = extensionLoader.getExtension("bumblebee");
        robot1.sayHello();
        Robot robot2 = extensionLoader.getExtension("optimusPrime");
        robot2.sayHello();
    }

    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,;]+\\s*");

    @Test
    public void testPattern() {
        String str = "aaa;,nnnn";
        String[] split = NAME_SEPARATOR.split(str);
        for (String s : split) {
            System.out.println(s);
        }
    }

    /**
     * 自动创建自适应类
     *      cn.antsing.spi.api.IDao文件配置如下：
     *          daoImpl=cn.antsing.spi.DaoImpl
     *          #adaptiveIDao=cn.antsing.spi.AdaptiveIDao
     *          daoImpl1=cn.antsing.spi.DaoImpl1
     *
     *
     *      自适应类的代码【通过debug调试获得】：
     *      package cn.antsing.spi.api;
     *      import com.alibaba.dubbo.common.extension.ExtensionLoader;
     *      public class IDao$Adpative implements cn.antsing.spi.api.IDao {
     *          public void execute(java.lang.String arg0, com.alibaba.dubbo.common.URL arg1) {
     *              if (arg1 == null) throw new IllegalArgumentException("url == null");
     *              com.alibaba.dubbo.common.URL url = arg1;
     *              String extName = url.getParameter("i.dao");
     *              if(extName == null)
     *                  throw new IllegalStateException("Fail to get extension(cn.antsing.spi.api.IDao) name from url(" + url.toString() + ") use keys([i.dao])");
     *              cn.antsing.spi.api.IDao extension = (cn.antsing.spi.api.IDao)ExtensionLoader.getExtensionLoader(
     *                  cn.antsing.spi.api.IDao.class).getExtension(extName);extension.execute(arg0, arg1);
     *          }
     *      }
     *
     *      通过上一步才知道要传的参数名称是：i.dao, 目前不还不知道怎么来的/todo
     */
    @Test
    public void testDubboSetInjectWithAutoAdaptive() {
        ExtensionLoader<IService> extensionLoader = ExtensionLoader.getExtensionLoader(IService.class);
        IService iService = extensionLoader.getExtension("iService");
        Map<String, String> args = new HashMap<>();
        args.put("i.dao", "daoImpl");
        URL url = new URL("", "", 2, args);
        String className = iService.insertData(" 123 ", url);
        System.out.println("className:"+className);
        Assert.assertTrue(className.contains("$"));
    }

    /**
     * 手动创建自适应类
     *      cn.antsing.spi.api.IDao文件配置如下：
     *          daoImpl=cn.antsing.spi.DaoImpl
     *          adaptiveIDao=cn.antsing.spi.AdaptiveIDao
     *          daoImpl1=cn.antsing.spi.DaoImpl1
     */
    @Test
    public void testDubboSetInjectWithCustomAdaptive() {
        ExtensionLoader<IService> extensionLoader = ExtensionLoader.getExtensionLoader(IService.class);
        IService iService = extensionLoader.getExtension("iService");
        Map<String, String> args = new HashMap<>();
        args.put("i.dao", "daoImpl1");
        URL url = new URL("", "", 2, args);
        String className = iService.insertData(" 123 ", url);
        Assert.assertTrue(!className.contains("$"));
    }

}
