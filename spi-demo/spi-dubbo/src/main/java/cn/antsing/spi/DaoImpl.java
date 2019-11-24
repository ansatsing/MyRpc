package cn.antsing.spi;

import cn.antsing.spi.api.IDao;

//@Adaptive
public class DaoImpl implements IDao {
    @Override
    public void execute(String sql) {
        System.out.println("insert data into database with sql:"+sql);
    }
}
