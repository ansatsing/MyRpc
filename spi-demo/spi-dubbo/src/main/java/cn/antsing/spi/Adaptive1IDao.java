//package cn.antsing.spi;
//
//import cn.antsing.spi.api.IDao;
//import com.alibaba.dubbo.common.extension.Adaptive;
//import com.alibaba.dubbo.common.extension.ExtensionLoader;
//
//@Adaptive
//public class Adaptive1IDao implements IDao {
//    @Override
//    public void execute(String sql,String url) {
//        ExtensionLoader<IDao> loader = ExtensionLoader.getExtensionLoader(IDao.class);
//        IDao iDao = loader.getExtension(url);
//        iDao.execute(sql,url);
//    }
//}
