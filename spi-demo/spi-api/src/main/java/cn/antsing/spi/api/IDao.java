package cn.antsing.spi.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface IDao {
    @Adaptive
    void execute(String sql, URL url);
}
