package cn.antsing.spi;

import cn.antsing.spi.api.IDao;
import com.alibaba.dubbo.common.URL;

public class DaoImpl implements IDao {
    @Override
    public void execute(String sql,URL url) {
        System.out.println("insert data into database with sql:"+sql);
    }
}
