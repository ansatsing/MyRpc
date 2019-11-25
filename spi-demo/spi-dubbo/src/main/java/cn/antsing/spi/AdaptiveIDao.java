package cn.antsing.spi;

import cn.antsing.spi.api.IDao;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

@Adaptive
public class AdaptiveIDao implements IDao {
    @Override
    public void execute(String sql, URL url) {
        System.out.println("AdaptiveIDao...............");
        ExtensionLoader<IDao> loader = ExtensionLoader.getExtensionLoader(IDao.class);
        IDao iDao = loader.getExtension(url.getParameter("i.dao"));
        iDao.execute(sql,url);
    }
}
