package cn.antsing.spi;

import cn.antsing.spi.api.IDao;
import cn.antsing.spi.api.IService;

public class ServiceImpl implements IService {
    private IDao iDao;
    @Override
    public void insertData(String data) {
        System.out.println("doing data operate:"+data);
        iDao.execute("insert into dd values(data)");
    }

    public void setIDao(IDao iDao) {
        this.iDao = iDao;
    }
}
