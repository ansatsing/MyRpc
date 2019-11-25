package cn.antsing.spi;

import cn.antsing.spi.api.IDao;
import cn.antsing.spi.api.IService;
import com.alibaba.dubbo.common.URL;

public class ServiceImpl implements IService {
    private IDao iDao;
    @Override
    public String insertData(String data,URL url) {
        System.out.println("doing data operate:"+data);
        iDao.execute(data,url);
        Class clazz = iDao.getClass();
        return clazz.getSimpleName();
    }

    public void setIDao(IDao iDao) {
        this.iDao = iDao;
    }
}
